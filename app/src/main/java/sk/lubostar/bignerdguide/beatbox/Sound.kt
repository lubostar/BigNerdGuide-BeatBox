package sk.lubostar.bignerdguide.beatbox

class Sound(val assetPath: String, var id: Int? = null) {
    companion object{
        private const val WAV = ".wav"
    }

    val name = assetPath.split("/").last().removeSuffix(WAV)
}