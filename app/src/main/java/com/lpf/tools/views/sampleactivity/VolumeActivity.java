package com.lpf.tools.views.sampleactivity;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.lpf.tools.R;
import com.lpf.tools.views.VisualizerVolumeView;

public class VolumeActivity extends Activity {

    private static final float VISUALIZER_HEIGHT_DIP = 100f;//频谱View高度

    private MediaPlayer mMediaPlayer;   //音频
    private Visualizer mVisualizer;     //频谱器

    LinearLayout mLayout;
    VisualizerVolumeView mBaseVisualizerView; //能量柱图

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏

        mLayout = new LinearLayout(this);//代码创建布局
        mLayout.setOrientation(LinearLayout.VERTICAL);//设置为线性布局-上下排列
        mLayout.setGravity(Gravity.CENTER);
        setContentView(mLayout);//将布局添加到 Activity

        setVolumeControlStream(AudioManager.STREAM_MUSIC);//设置音频流，--Stream_music:音乐回放媒体声音

        mMediaPlayer = MediaPlayer.create(this, R.raw.dali);//实例化音乐并添加音频

        setupVisualizerFxAndUi();   //添加能量柱到界面上

        mVisualizer.setEnabled(true);
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });

        mMediaPlayer.start();
        mMediaPlayer.setLooping(true);//循环播放
    }

    /**
     * 生成一个VisualizerView对象，使音频频谱的波段能够反映到VisualizerView上
     */
    private void setupVisualizerFxAndUi() {

        mBaseVisualizerView = new VisualizerVolumeView(this);

        mBaseVisualizerView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,//宽度
                (int) (VISUALIZER_HEIGHT_DIP * getResources().getDisplayMetrics().density)//高度
        ));
        //将频谱View添加到布局
        mLayout.addView(mBaseVisualizerView);
//        mBaseVisualizerView = (VisualizerView) findViewById(R.id.id_musicview);

        //实例化Visualizer,参数SessionId可以通过MediaPlayer的对象获得
        mVisualizer = new Visualizer(mMediaPlayer.getAudioSessionId());
        //采样--参数内必须是2的位数，入64 ，128，256，512，1024
        mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
        //设置允许波形表示，并且捕获它
        mBaseVisualizerView.setVisualizer(mVisualizer);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isFinishing() && mMediaPlayer!=null){
            mVisualizer.release();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
