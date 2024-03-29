package sk.lubostar.bignerdguide.beatbox

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log
import java.io.IOException
import java.lang.Exception

class BeatBox(private val assets: AssetManager) {
    companion object{
        private const val TAG = "Beat Box"
        private const val SOUNDS_FOLDER = "sample_sounds"
        private const val MAX_SOUNDS = 5
    }

    val sounds: List<Sound>
    private val soundPool = SoundPool.Builder().setMaxStreams(MAX_SOUNDS).build()

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
            val sound = Sound(assetPath)
            try {
                load(sound)
                sounds.add(sound)
            }catch (ioe: IOException){
                Log.e(TAG, "Could not load sound $filename", ioe)
            }
        }

        return sounds
    }

    private fun load(sound: Sound){
        val afd: AssetFileDescriptor = assets.openFd(sound.assetPath)
        val soundId = soundPool.load(afd, 1)
        sound.id = soundId
    }

    fun play(sound: Sound) = sound.id?.let {
        soundPool.play(it, 1.0f, 1.0f, 1, 0, 1.0f)
    }

    fun release() = soundPool.release()
}