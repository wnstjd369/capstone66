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
import android.view.WindowManager
import com.example.capstone3.Main3Activity
import com.example.capstone2.activity.EnterPinActivity
import kotlinx.android.synthetic.main.activity_main2.*
import org.json.JSONObject


class Main2Activity : AppCompatActivity() {
    val fragment = MyPreferenceFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        // preferenceContent FrameLayout 영역을 PreferenceFragment 로 교체
        var intent2 = Intent(this, EnterPinActivity::class.java)
        startActivity(intent2)
        fragmentManager.beginTransaction().replace(R.id.preferenceContent, fragment).commit()


        setButton.setOnClickListener{
            var intent = Intent(this,locker::class.java)
            startActivity(intent)
        }
        button10.setOnClickListener{
            var intent3 = Intent(this,Main3Activity::class.java)
            startActivity(intent3)
        }
    }

    class MyPreferenceFragment : PreferenceFragment() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            // 환경설정 리소스 파일 적용
            addPreferencesFromResource(R.xml.pref)
            // 퀴즈 종류 요약정보에, 현재 선택된 항목을 보여주는 코드
//            val categoryPref = findPreference("category") as MultiSelectListPreference
//            categoryPref.summary = categoryPref.values.joinToString(", ")
//            // 환경설정 정보값이 변경될때에도 요약정보를 변경하도록 리스너 등록
//            categoryPref.setOnPreferenceChangeListener { preference, newValue ->
//                // newValue 파라미터가 HashSet 으로 캐스팅이 실패하면 리턴
//                val newValueSet = newValue as? HashSet<*>
//                        ?: return@setOnPreferenceChangeListener true
//                // 선택된 퀴즈종류로 요약정보 보여줌
//                categoryPref.summary = newValue.joinToString(", ")
//                true
//            }
//             퀴즈 잠금화면 사용 스위치 객체 가져옴
            val useLockScreenPref = findPreference("useLockScreen") as SwitchPreference
            // 클릭되었을때의 이벤트 리스너 코드 작성
            useLockScreenPref.setOnPreferenceClickListener {
                when {
                    // 퀴즈 잠금화면 사용이 체크된 경우 LockScreenService 실행
                    useLockScreenPref.isChecked -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            activity.startForegroundService(Intent(activity, LockScreenService::class.java))
                        } else {
                            activity.startService(Intent(activity, LockScreenService::class.java))
                        }
                    }
                    // 퀴즈 잠금화면 사용이 체크 해제된 경우 LockScreenService 중단
                    else -> activity.stopService(Intent(activity, LockScreenService::class.java))
                }
                true
            }
            // 앱이 시작 되었을때 이미 퀴즈잠금화면 사용이 체크되어있으면 서비스 실행
            if (useLockScreenPref.isChecked) {
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