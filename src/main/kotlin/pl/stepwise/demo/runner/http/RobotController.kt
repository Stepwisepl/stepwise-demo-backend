package pl.stepwise.demo.runner.http

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.util.MimeTypeUtils
import org.springframework.web.bind.annotation.*
import pl.stepwise.demo.common.dto.SimpleErrorMessage
import pl.stepwise.demo.runner.dto.MoveDto
import pl.stepwise.demo.runner.dto.NewRobotStatusDto
import pl.stepwise.demo.runner.dto.RobotStatusDto
import pl.stepwise.demo.runner.service.RobotService

@RestController
@RequestMapping("/robot")
class RobotController(
    private val robotService: RobotService
) {

    @Operation(
        summary = "Get Robot Status",
        parameters = [
            Parameter(
                name = "id",
                required = true,
                `in` = ParameterIn.PATH,
                schema = Schema(type = "integer")
            )
        ],
        responses = [
            ApiResponse(
                description = "Robot Status successfully fetched",
                responseCode = "200",
                content = [Content(
                    mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = RobotStatusDto::class)
                )]
            ),
            ApiResponse(
                description = "Robot Status not found.",
                responseCode = "404",
                content = [Content(
                    mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = SimpleErrorMessage::class)
                )]
            ),
            ApiResponse(
                description = "Internal server error",
                responseCode = "500",
                content = [Content(
                    mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = SimpleErrorMessage::class)
                )]
            )
        ]
    )
    @GetMapping("{id}")
    fun fetchRobotStatus(@PathVariable id: Long): RobotStatusDto = robotService.getRobotStatus(id)

    @Operation(
        summary = "Create Robot Status",
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = [Content(
                mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                schema = Schema(implementation = NewRobotStatusDto::class)
            )]
        ),
        responses = [
            ApiResponse(
                description = "Robot Status successfully fetched",
                responseCode = "200",
                content = [Content(
                    mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = RobotStatusDto::class)
                )]
            ),
            ApiResponse(
                description = "Internal server error",
                responseCode = "500",
                content = [Content(
                    mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = SimpleErrorMessage::class)
                )]
            )
        ]
    )
    @PostMapping
    fun createRobotStatus(@RequestBody newRobotStatusDto: NewRobotStatusDto) =
        robotService.createRobotStatus(newRobotStatusDto)

    @Operation(
        summary = "Move Robot",
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = [Content(
                mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                array = ArraySchema(schema = Schema(implementation = NewRobotStatusDto::class))
            )]
        ),
        responses = [
            ApiResponse(
                description = "Robot movement completed",
                responseCode = "200",
                content = [Content(
                    mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = RobotStatusDto::class)
                )]
            ),
            ApiResponse(
                description = "Robot movement validation failed",
                responseCode = "400",
                content = [Content(
                    mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = RobotStatusDto::class)
                )]
            ),
            ApiResponse(
                description = "Internal server error",
                responseCode = "500",
                content = [Content(
                    mediaType = MimeTypeUtils.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = SimpleErrorMessage::class)
                )]
            )
        ]
    )
    @PostMapping("{id}:move")
    fun moveRobot(@PathVariable id: Long, @RequestBody movements: List<MoveDto>): RobotStatusDto {
        return robotService.moveRobot(id, movements)
    }

}