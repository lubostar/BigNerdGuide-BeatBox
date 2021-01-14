package sk.lubostar.bignerdguide.beatbox

class Sound(assetPath: String) {
    companion object{
        private const val WAV = ".wav"
    }

    val name = assetPath.split("/").last().removeSuffix(WAV)
}