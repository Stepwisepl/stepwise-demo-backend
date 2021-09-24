package pl.stepwise.demo.runner.dto

import pl.stepwise.demo.definition.dto.RobotDefinitionDto

data class RobotStatusDto(
    val id: Long?,
    val position: PointDto,
    val robotDefinition: RobotDefinitionDto
)