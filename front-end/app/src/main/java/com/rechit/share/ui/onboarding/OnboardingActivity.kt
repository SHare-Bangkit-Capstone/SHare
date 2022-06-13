package com.rechit.share.ui.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.rechit.share.R
import com.rechit.share.databinding.ActivityOnboardingBinding
import com.rechit.share.ui.UserPreference
import com.rechit.share.ui.auth.LoginActivity
import com.rechit.share.ui.custom.CustomViewPagerAdapter
import com.rechit.share.ui.viewmodel.MainViewModel
import com.rechit.share.ui.viewmodel.ViewModelFactory


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "is_first_time_launch")

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var customViewPagerAdapter: CustomViewPagerAdapter
    private lateinit var layouts: IntArray

    private var viewPagerPageChangeListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageSelected(position: Int) {
            addBottomDots(position)
            if (position == layouts.size - 1) {
                binding.btnNext.visibility = View.INVISIBLE
                binding.btnStart.visibility = View.VISIBLE
            } else {
                binding.btnNext.visibility = View.VISIBLE
                binding.btnStart.visibility = View.INVISIBLE
            }
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
        override fun onPageScrollStateChanged(arg0: Int) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        val pref = UserPreference.getInstance(dataStore)
        val mainViewModel = ViewModelProvider(this, ViewModelFactory(pref))[MainViewModel::class.java]

        mainViewModel.isFirstTimeLaunch().observe(this
        ) { isFirstTimeLaunch: Boolean ->
            if (!isFirstTimeLaunch) {
                mainViewModel.setFirstTimeLaunch(false)
                startActivity(Intent(this, LoginActivity::class.java))
                finish();
            }  else {
                setContentView(binding.root)
            }
        }
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        layouts = intArrayOf(
            com.rechit.share.R.layout.fragment_onboarding1,
            com.rechit.share.R.layout.fragment_onboarding2,
            com.rechit.share.R.layout.fragment_onboarding3
        )
        customViewPagerAdapter = CustomViewPagerAdapter(layouts, this)
        addBottomDots(0);
        binding.viewPager.adapter = customViewPagerAdapter
        binding.viewPager.addOnPageChangeListener(viewPagerPageChangeListener)
        binding.btnStart.setOnClickListener {
            mainViewModel.setFirstTimeLaunch(false)
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        binding.btnNext.setOnClickListener {
            val current: Int = binding.viewPager.currentItem + 1
            if (current < layouts.size) {
                // move to next screen
                binding.viewPager.currentItem = current
            } else {
                mainViewModel.setFirstTimeLaunch(false)
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun addBottomDots(currentPage: Int) {
        val dots = arrayOfNulls<TextView>(layouts.size)

        val colorsActive = resources.getIntArray(R.array.array_dot_inactive)
        val colorsInactive = resources.getIntArray(R.array.array_dot_active)

        binding.layoutDots.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(this)
            dots[i]!!.text = Html.fromHtml("&#8226;")
            dots[i]!!.textSize = 70f
            dots[i]!!.setTextColor(colorsInactive[currentPage])
            binding.btnStart.setTextColor(colorsInactive[currentPage])
            binding.layoutDots.addView(dots[i])
        }

        if (dots.isNotEmpty()) dots[currentPage]!!.setTextColor(colorsActive[currentPage])

    }

}
