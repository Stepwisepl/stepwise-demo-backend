package pl.stepwise.demo.runner.model

import pl.stepwise.demo.definition.model.RobotDefinition
import javax.persistence.*

@Entity
open class RobotStatus(

    @OneToOne(cascade = [CascadeType.ALL], optional = false, orphanRemoval = true)
    @JoinColumn(name = "current_position_id", nullable = false)
    open var currentPosition: Point,

    @ManyToOne
    @JoinColumn(name = "definition_id")
    open var definition: RobotDefinition
) {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    open var id: Long? = null
}

