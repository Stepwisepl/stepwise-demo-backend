package pl.stepwise.demo.runner.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.stepwise.demo.definition.dto.DimensionsDto
import pl.stepwise.demo.definition.dto.RobotDefinitionDto
import pl.stepwise.demo.definition.service.DefinitionService
import pl.stepwise.demo.runner.dto.MoveDto
import pl.stepwise.demo.runner.dto.NewRobotStatusDto

@SpringBootTest
class RobotServiceFunctionalTest {

    @Autowired
    lateinit var robotService: RobotService

    @Autowired
    lateinit var definitionService: DefinitionService

    @Test
    fun test() {
        val definition =
            definitionService.createNewDefinition(RobotDefinitionDto(null, DimensionsDto(0, 10, 0, 10, 0, 10)))
        val status = robotService.createRobotStatus(NewRobotStatusDto(definition.id!!))
        assertThrows<IllegalStateException> {
            robotService.moveRobot(status.id!!, listOf(MoveDto(100, 100, 100)))
        }
    }
}