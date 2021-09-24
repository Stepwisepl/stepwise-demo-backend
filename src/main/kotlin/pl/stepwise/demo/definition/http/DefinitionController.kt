package pl.stepwise.demo.definition.http

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*
import pl.stepwise.demo.common.dto.SimpleErrorMessage
import pl.stepwise.demo.definition.dto.RobotDefinitionDto
import pl.stepwise.demo.definition.service.DefinitionService

@RestController
@RequestMapping("/robot/definition")
class DefinitionController(
    private val definitionService: DefinitionService
) {
    @Operation(
        summary = "List of Robot definitions",
        responses = [
            ApiResponse(
                description = "List successfully fetched",
                responseCode = "200",
                content = [Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    array = ArraySchema(schema = Schema(implementation = RobotDefinitionDto::class))
                )]
            ),
            ApiResponse(
                description = "Internal server error",
                responseCode = "500",
                content = [Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = SimpleErrorMessage::class)
                )]
            )
        ]
    )
    @GetMapping
    fun getDefinitions(): List<RobotDefinitionDto> = definitionService.getDefinitions()

    @Operation(
        summary = "Get Robot definition",
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
                description = "Robot definition successfully fetched",
                responseCode = "200",
                content = [Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = RobotDefinitionDto::class)
                )]
            ),
            ApiResponse(
                description = "Robot definition not found.",
                responseCode = "404",
                content = [Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = SimpleErrorMessage::class)
                )]
            ),
            ApiResponse(
                description = "Internal server error",
                responseCode = "500",
                content = [Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = SimpleErrorMessage::class)
                )]
            )
        ]
    )
    @GetMapping("{id}")
    fun getDefinition(@PathVariable id: Long): RobotDefinitionDto = definitionService.getDefinition(id)

    @Operation(
        summary = "Get Robot definition",
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = [Content(
                mediaType = APPLICATION_JSON_VALUE,
                schema = Schema(implementation = RobotDefinitionDto::class)
            )]
        ),
        responses = [
            ApiResponse(
                description = "Robot definition successfully created",
                responseCode = "200",
                content = [Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = RobotDefinitionDto::class)
                )]
            ),
            ApiResponse(
                description = "Robot definition is not valid. Details in response.",
                responseCode = "400",
                content = [Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = SimpleErrorMessage::class)
                )]
            ),
            ApiResponse(
                description = "Internal server error",
                responseCode = "500",
                content = [Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = SimpleErrorMessage::class)
                )]
            )
        ]
    )
    @PostMapping
    fun createNewDefinition(@RequestBody definition: RobotDefinitionDto): RobotDefinitionDto =
        definitionService.createNewDefinition(definition)

    @Operation(
        summary = "Get Robot definition",
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = [Content(
                mediaType = APPLICATION_JSON_VALUE,
                schema = Schema(implementation = RobotDefinitionDto::class)
            )]
        ),
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
                description = "Robot definition successfully updated",
                responseCode = "200",
                content = [Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = RobotDefinitionDto::class)
                )]
            ),
            ApiResponse(
                description = "Robot definition is not valid. Details in response.",
                responseCode = "400",
                content = [Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = SimpleErrorMessage::class)
                )]
            ),
            ApiResponse(
                description = "Internal server error",
                responseCode = "500",
                content = [Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = SimpleErrorMessage::class)
                )]
            )
        ]
    )
    @PutMapping("{id}")
    fun updateDefinition(@PathVariable id: Long, @RequestBody definition: RobotDefinitionDto): RobotDefinitionDto =
        definitionService.updateDefinition(id, definition)

    @Operation(
        summary = "Delete Robot definition",
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
                description = "Robot definition successfully deleted",
                responseCode = "200",
                content = [Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = RobotDefinitionDto::class)
                )]
            ),
            ApiResponse(
                description = "Internal server error",
                responseCode = "500",
                content = [Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = SimpleErrorMessage::class)
                )]
            )
        ]
    )
    @DeleteMapping("{id}")
    fun deleteDefinition(@PathVariable id: Long): Unit =
        definitionService.deleteDefinition(id)
}