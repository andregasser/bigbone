package social.bigbone

import kotlinx.serialization.json.Json

internal val JsonSerializer: Json = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
    coerceInputValues = true
}