package social.bigbone.api

/**
 * Represents a pageable result.
 */
class Pageable<T>(val part: List<T>, val link: Link?) {
    fun nextRange(limit: Int = 20): Range = Range(link?.maxId, limit = limit)
    fun prevRange(limit: Int = 20): Range = Range(sinceId = link?.sinceId, limit = limit)
    fun toRange(limit: Int = 20): Range = Range(link?.maxId, link?.minId, link?.sinceId, limit)
}
