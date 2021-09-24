package pl.stepwise.demo.definition.model

import javax.persistence.*

@Entity
open class RobotDefinition(
    @OneToOne(cascade = [CascadeType.ALL], optional = false, orphanRemoval = true)
    @JoinColumn(name = "dimensions_id", nullable = false)
    open var dimensions: Dimensions,
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    open var id: Long? = null
)