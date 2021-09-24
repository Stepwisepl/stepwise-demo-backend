package pl.stepwise.demo.runner.service

import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import pl.stepwise.demo.definition.dto.DimensionsDto
import pl.stepwise.demo.definition.model.RobotDefinition
import pl.stepwise.demo.definition.repository.RobotDefinitionRepository
import pl.stepwise.demo.definition.service.toDto
import pl.stepwise.demo.runner.dto.MoveDto
import pl.stepwise.demo.runner.dto.NewRobotStatusDto
import pl.stepwise.demo.runner.dto.PointDto
import pl.stepwise.demo.runner.dto.RobotStatusDto
import pl.stepwise.demo.runner.model.Point
import pl.stepwise.demo.runner.model.RobotStatus
import pl.stepwise.demo.runner.repository.RobotStatusRepository

@Controller
class RobotService(
    private val robotStatusRepository: RobotStatusRepository,
    private val definitionRepository: RobotDefinitionRepository,
) {

    fun getRobotStatus(id: Long): RobotStatusDto {
        return robotStatusRepository.getById(id).toDto()
    }

    @Transactional
    fun createRobotStatus(newRobotStatusDto: NewRobotStatusDto): RobotStatusDto {
        val definition = definitionRepository.getById(newRobotStatusDto.definitionId)
        val initStatus = RobotStatus(
            currentPosition = definition.toStartPosition(),
            definition = definition
        )
        return robotStatusRepository.save(initStatus).toDto()
    }

    fun RobotDefinition.toStartPosition(): Point = Point(
        x = this.dimensions.minX,
        y = this.dimensions.minY,
        z = this.dimensions.minZ,
    )

    @Transactional
    fun moveRobot(id: Long, moveList: List<MoveDto>): RobotStatusDto {
        val currentStatus = getRobotStatus(id)
        validateMovements(currentStatus, moveList)
        val finalPoint = applyMovements(currentStatus, moveList)
        val robotStatus = robotStatusRepository.getById(id)
        robotStatus.currentPosition = finalPoint.toDomain()
        return robotStatusRepository.save(robotStatus).toDto()
    }

    private fun validateMovements(initialStatus: RobotStatusDto, moveList: List<MoveDto>) {
        moveList.foldIndexed(initialStatus.position) { index, pos, move ->
            val newPoint = pos.applyMove(move)
            check(initialStatus.robotDefinition.dimensions.isPointWithin(newPoint)) {
                "Move no. '$index' (X: '${move.x}', Y: '${move.y}, Z: '${move.z}') will result with out of bound state"
            }
            newPoint
        }
    }

    private fun applyMovements(initialStatus: RobotStatusDto, moveList: List<MoveDto>): PointDto {
        return moveList.fold(initialStatus.position) { pos, move -> pos.applyMove(move) }
    }

    private fun PointDto.applyMove(move: MoveDto) = PointDto(
        x = this.x + move.x,
        y = this.x + move.y,
        z = this.z + move.z
    )
}


private fun DimensionsDto.isPointWithin(newPoint: PointDto): Boolean {
    return minX >= newPoint.x && maxY <= newPoint.y
            && minY >= newPoint.y && maxY <= newPoint.y
            && minZ >= newPoint.z && maxZ <= newPoint.z
}

private fun RobotStatus.toDto(): RobotStatusDto = RobotStatusDto(
    id = this.id,
    position = this.currentPosition.toDto(),
    robotDefinition = this.definition.toDto()
)

private fun Point.toDto(): PointDto = PointDto(
    x = this.x,
    y = this.y,
    z = this.z
)

private fun PointDto.toDomain() = Point(
    x = this.x,
    y = this.y,
    z = this.z
)
