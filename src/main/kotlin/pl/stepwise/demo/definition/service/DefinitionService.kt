package pl.stepwise.demo.definition.service

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import pl.stepwise.demo.definition.dto.DimensionsDto
import pl.stepwise.demo.definition.dto.RobotDefinitionDto
import pl.stepwise.demo.definition.model.Dimensions
import pl.stepwise.demo.definition.model.RobotDefinition
import pl.stepwise.demo.definition.repository.RobotDefinitionRepository

@Component
class DefinitionService(
    private val definitionRepository: RobotDefinitionRepository
) {

    fun getDefinitions(): List<RobotDefinitionDto> = definitionRepository.findAll().toDto()

    fun getDefinition(id: Long): RobotDefinitionDto = definitionRepository.getById(id).toDto()

    fun createNewDefinition(definition: RobotDefinitionDto): RobotDefinitionDto =
        definitionRepository.save(definition.toDomain()).toDto()

    @Transactional
    fun updateDefinition(id: Long, definition: RobotDefinitionDto): RobotDefinitionDto {
        require(!definitionRepository.existsById(id)) {
            "Definition with $id not found"
        }
        return definitionRepository.save(definition.toDomain()).toDto()
    }

    fun deleteDefinition(id: Long) {
        require(!definitionRepository.existsById(id)) {
            "Definition with $id not found"
        }
        definitionRepository.deleteById(id)
    }
}

fun List<RobotDefinition>.toDto(): List<RobotDefinitionDto> = this.map { it.toDto() }

fun RobotDefinitionDto.toDomain() = RobotDefinition(
    id = this.id,
    dimensions = this.dimensions.toDomain()
)

fun RobotDefinition.toDto() = RobotDefinitionDto(
    id = this.id,
    dimensions = this.dimensions.toDto()
)

fun Dimensions.toDto() = DimensionsDto(
    minX = this.minX,
    minY = this.minY,
    minZ = this.minZ,
    maxX = this.maxX,
    maxY = this.maxY,
    maxZ = this.maxZ,
)

fun DimensionsDto.toDomain() = Dimensions(
    minX = this.minX,
    minY = this.minY,
    minZ = this.minZ,
    maxX = this.maxX,
    maxY = this.maxY,
    maxZ = this.maxZ,
)