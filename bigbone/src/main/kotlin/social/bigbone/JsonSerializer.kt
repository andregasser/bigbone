package social.bigbone

import kotlinx.serialization.json.Json

internal val JSON_SERIALIZER: Json = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
    coerceInputValues = true
}
