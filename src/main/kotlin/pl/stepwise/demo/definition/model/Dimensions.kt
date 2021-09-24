package pl.stepwise.demo.definition.model

import javax.persistence.*

@Table(name = "Dimensions", indexes = [
    Index(name = "idx_dimensions_minx_maxx_miny", columnList = "minX, maxX, minY, maxY, minZ")
])
@Entity
class Dimensions(
    @Column(name = "minX", nullable = false)
    val minX: Int,
    @Column(name = "maxX", nullable = false)
    val maxX: Int,
    @Column(name = "minY", nullable = false)
    val minY: Int,
    @Column(name = "maxY", nullable = false)
    val maxY: Int,
    @Column(name = "minZ", nullable = false)
    val minZ: Int,
    @Column(name = "maxZ", nullable = false)
    val maxZ: Int,
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    var id: Long? = null
) {
    init {
        require(maxX >= minX) {
            "Max of X needs to equal or greater that min"
        }
        require(maxY >= minY) {
            "Max of Y needs to equal or greater that min"
        }
        require(maxZ >= minZ) {
            "Max of Z needs to equal or greater that min"
        }
    }
}
