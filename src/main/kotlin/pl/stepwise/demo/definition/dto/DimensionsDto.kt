package pl.stepwise.demo.definition.dto

import io.swagger.v3.oas.annotations.Parameter

data class DimensionsDto(
    @Parameter(name = "minX", description = "Minimal value of X")
    val minX: Int,
    @Parameter(name = "maxX", description = "Maximal value of X")
    val maxX: Int,
    @Parameter(name = "minY", description = "Minimal value of Y")
    val minY: Int,
    @Parameter(name = "maxY", description = "Maximal value of Y")
    val maxY: Int,
    @Parameter(name = "minZ", description = "Minimal value of Z")
    val minZ: Int,
    @Parameter(name = "maxZ", description = "Maximal value of Z")
    val maxZ: Int
)