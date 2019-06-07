package com.example.capstone3
import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.preference.MultiSelectListPreference
import android.preference.PreferenceFragment
import android.preference.SwitchPreference
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.example.capstone2.activity.EnterPinActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main2.*
import org.jetbrains.anko.toast
import org.json.JSONObject
import java.util.concurrent.locks.Lock

var lock:String =""
var SName:String =""

class Main2Activity : AppCompatActivity() {
    val fragment = MyPreferenceFragment()

    private lateinit var database: DatabaseReference// ...

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var ss:String =""
        var name:String=""
        ss = intent.getStringExtra("LOCK")
        name = intent.getStringExtra("Name")
        lock = ss
        SName = name
        // preferenceContent FrameLayout 영역을 PreferenceFragment 로 교체
        var intent2 = Intent(this, EnterPinActivity::class.java)
        startActivity(intent2)
        fragmentManager.beginTransaction().replace(R.id.preferenceContent, fragment).commit()
        setButton.setOnClickListener{
            var intent = Intent(this,locker::class.java)
            startActivity(intent)

        }
        var intent3 = Intent(this, camera::class.java)
        intent3.putExtra("Name",SName)
        startActivity(intent3)
    }

    class MyPreferenceFragment : PreferenceFragment() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            Log.d("LOCKCLASS","on")

            // 환경설정 리소스 파일 적용
            addPreferencesFromResource(R.xml.pref)
//             퀴즈 잠금화면 사용 스위치 객체 가져옴
         //   val useLockScreenPref = findPreference("useLockScreen")
            // 클릭되었을때의 이벤트 리스너 코드 작성
           // useLockScreenPref.setOnPreferenceClickListener {
                Log.d("LOCKSTART","on")
                when (lock) {
                    // 퀴즈 잠금화면 사용이 체크된 경우 LockScreenService 실행
                    "1" -> {
                        toast("LOCK SCREEN ON !")
                        Log.d("LOCK","on")
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            activity.startForegroundService(Intent(activity, LockScreenService::class.java))
                        } else {
                            Log.d("LOCK","off")
                            activity.startService(Intent(activity, LockScreenService::class.java))
                        }
                    }
                    // 퀴즈 잠금화면 사용이 체크 해제된 경우 LockScreenService 중단
                    "0" -> {
                        toast("LOCK SCREEN OFF !")
                        activity.stopService(Intent(activity, LockScreenService::class.java))

                    }
              //      }
               //0000000000000000000000000 true
            }
            // 앱이 시작 되었을때 이미 퀴즈잠금화면 사용이 체크되어있으면 서비스 실행
            if (lock == "1") {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    activity.startForegroundService(Intent(activity, LockScreenService::class.java))
                } else {
                    activity.startService(Intent(activity, LockScreenService::class.java))
                }
            }
        }

        class QuizLockerActivity : AppCompatActivity() {
            var quiz: JSONObject? = null
            // 정답횟수 저장 SharedPreference
            val wrongAnswerPref by lazy { getSharedPreferences("wrongAnswer", Context.MODE_PRIVATE) }
            // 오답횟수 저장 SharedPreference
            val correctAnswerPref by lazy { getSharedPreferences("correctAnswer", Context.MODE_PRIVATE) }


        }
    }
}