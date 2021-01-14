package sk.lubostar.bignerdguide.beatbox

import android.content.res.AssetManager
import android.util.Log
import java.lang.Exception

class BeatBox(private val assets: AssetManager) {
    companion object{
        private const val TAG = "Beat Box"
        private const val SOUNDS_FOLDER = "sample_sounds"
    }

    val sounds: List<Sound>

    init {
        sounds = loadSounds()
    }

    private fun loadSounds(): List<Sound> {
        val soundNames: Array<String>
        try{
            soundNames = assets.list(SOUNDS_FOLDER)!!
        } catch (e: Exception){
            Log.e(TAG, "Could not list assets", e)
            return emptyList()
        }
        val sounds = mutableListOf<Sound>()
        soundNames.forEach { filename ->
            val assetPath = "$SOUNDS_FOLDER/$filename"
            sounds.add(Sound(assetPath))
        }

        return sounds
    }
}