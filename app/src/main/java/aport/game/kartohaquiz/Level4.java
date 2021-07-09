package aport.game.kartohaquiz;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level4 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft;
    public int numRight;
    Array array = new Array();
    Random random = new Random();
    public int count = 0;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageView background = (ImageView) findViewById(R.id.background);
        background.setImageResource(R.drawable.main_background);

        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level4);
        text_levels.setTextColor(R.color.black95);

        final ImageView img_left = (ImageView) findViewById(R.id.img_left);
        img_left.setClipToOutline(true);

        final ImageView img_right = (ImageView) findViewById(R.id.img_right);
        img_right.setClipToOutline(true);

        final TextView text_left = findViewById(R.id.text_left);
        text_left.setTextColor(R.color.black95);
        final TextView text_right = findViewById(R.id.text_right);
        text_right.setTextColor(R.color.black95);


        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.previewdialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        ImageView previewimg = (ImageView) dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.previewimgone); // поменять на вторую превьюху

        LinearLayout dialogfon = (LinearLayout) dialog.findViewById(R.id.dialogfon);
        dialogfon.setBackgroundResource(R.drawable.previewbackgroundone);

        TextView textdescription = (TextView) dialog.findViewById(R.id.textdescription);
        textdescription.setText(R.string.levelthree);

        TextView btnclose = (TextView) dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level4.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });

        Button btncontinue = (Button) dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        dialog.show();

        //__________________________________________________________________________________________________
        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEnd.setContentView(R.layout.dialogend);
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false);

        LinearLayout dialogfonEnd = (LinearLayout) dialogEnd.findViewById(R.id.dialogfon);
        dialogfonEnd.setBackgroundResource(R.drawable.previewbackgroundone);

        TextView textdescriptionEnd = (TextView) dialogEnd.findViewById(R.id.textdescriptionEnd);
        textdescriptionEnd.setText(R.string.levelfourEnd);

        TextView btnclose2 = (TextView) dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level4.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialogEnd.dismiss();
            }
        });

        Button btncontinue2 = (Button) dialogEnd.findViewById(R.id.btncontinue);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level4.this, Level5.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialogEnd.dismiss();
            }
        });
//__________________________________________________________________________________________________

        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setBackgroundResource(R.drawable.button_stroke_black95_press_white);
        button_back.setTextColor(R.color.black95);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level4.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
                R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20,
        };

        final Animation a = AnimationUtils.loadAnimation(Level4.this, R.anim.alpha);

        numLeft = random.nextInt(10);
        img_left.setImageResource(array.images3[numLeft]);
        text_left.setText(array.texts3[numLeft]);

        numRight = random.nextInt(10);
        while (numLeft == numRight) {
            numRight = random.nextInt(10);
        }

        img_right.setImageResource(array.images3[numRight]);
        text_right.setText(array.texts3[numRight]);

        //левая кнопка начало
        img_left.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_right.setEnabled(false);
                    if (numLeft > numRight) {
                        img_left.setImageResource(R.drawable.yeah);
                    } else img_left.setImageResource(R.drawable.nope);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (numLeft > numRight) {
                        if (count < 20) {
                            count++;
                        }

                        for (int i = 0; i < 20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    } else {
                        if (count > 0) {
                            if (count == 1) {
                               // count = 0;
                            } else {
                                count -= 2;
                            }
                        }
                        for (int i = 0; i < 19; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }

                    if (count == 20) {

                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level", 1);

                        if (level < 4) {
                            //clean
                        } else {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 5);
                            editor.commit();
                        }

                        dialogEnd.show();
                    } else {
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images3[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts3[numLeft]);

                        numRight = random.nextInt(10);
                        while (numLeft == numRight) {
                            numRight = random.nextInt(10);
                        }

                        img_right.setImageResource(array.images3[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts3[numRight]);

                        img_right.setEnabled(true);

                    }
                }

                return true;
            }
        });
        //левая кнопка конец

        // правая кнопка начало
        img_right.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_left.setEnabled(false);
                    if (numLeft < numRight) {
                        img_right.setImageResource(R.drawable.yeah);
                    } else img_right.setImageResource(R.drawable.nope);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (numLeft < numRight) {
                        if (count < 20) {
                            count++;
                        }

                        for (int i = 0; i < 20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    } else {
                        if (count > 0) {
                            if (count == 1) {
                                // count = 0;
                            } else {
                                count -= 2;
                            }
                        }
                        for (int i = 0; i < 19; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if (count == 20) {

                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level", 1);

                        if (level < 4) {
                            //clean
                        } else {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level", 5);
                            editor.commit();
                        }

                        dialogEnd.show();
                    } else {
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images3[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts3[numLeft]);

                        numRight = random.nextInt(10);
                        while (numLeft == numRight) {
                            numRight = random.nextInt(10);
                        }

                        img_right.setImageResource(array.images3[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts3[numRight]);

                        img_left.setEnabled(true);

                    }
                }

                return true;
            }
        });
        // правая кнопка конец
    }
    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(Level4.this, GameLevels.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
