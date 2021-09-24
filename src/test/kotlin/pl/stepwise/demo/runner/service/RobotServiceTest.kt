package pl.stepwise.demo.runner.service

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import pl.stepwise.demo.definition.model.Dimensions
import pl.stepwise.demo.definition.model.RobotDefinition
import pl.stepwise.demo.definition.repository.RobotDefinitionRepository
import pl.stepwise.demo.runner.dto.MoveDto
import pl.stepwise.demo.runner.model.Point
import pl.stepwise.demo.runner.model.RobotStatus
import pl.stepwise.demo.runner.repository.RobotStatusRepository

class RobotServiceTest {

    private val robotStatusRepository = mock<RobotStatusRepository>()
    private val definitionRepository = mock<RobotDefinitionRepository>()

    private val cut = RobotService(robotStatusRepository, definitionRepository)

    @Test
    fun `GIVEN robot in valid position WHEN moving outside of range THEN exception thrown`() {
        whenever(robotStatusRepository.getById(eq(1L))).thenReturn(
            RobotStatus(
                Point(0, 0, 0), RobotDefinition(
                    Dimensions(0, 10, 0, 10, 0, 10),
                    1L
                )
            )
        )
        val ex = assertThrows<IllegalStateException> {
            cut.moveRobot(1L, moveList = listOf(MoveDto(1000, 1000, 1000)))
        }
        assertThat(ex).hasMessageContaining("Move no.")
    }

}