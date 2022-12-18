package aaa.bbb.registration

import android.media.Ringtone
import android.media.RingtoneManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AlarmActivity : AppCompatActivity() {
    private lateinit var ringtone: Ringtone

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        var notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringtone = RingtoneManager.getRingtone(this, notificationUri)
        if(ringtone == null) {
            notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
            ringtone = RingtoneManager.getRingtone(this, notificationUri)
        }
        if (ringtone != null) {
            ringtone.play()
        }
    }

    override fun onDestroy() {
        if(ringtone != null && ringtone.isPlaying) {
            ringtone.stop()
        }
        super.onDestroy()

    }
}