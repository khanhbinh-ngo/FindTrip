package com.example.findtrip;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);

        initUI();
    }

    private void initUI() {
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        ImageView notificationIcon = findViewById(R.id.notificationIcon);
        notificationIcon.setOnClickListener(v -> Toast.makeText(this, "Đã mở thông báo", Toast.LENGTH_SHORT).show());

        TextView markAllAsRead = findViewById(R.id.marksallasread);
        markAllAsRead.setOnClickListener(v -> {
            Toast.makeText(this, "Đã đánh dấu tất cả là đã đọc", Toast.LENGTH_SHORT).show();
            updateNotificationReadStatus();
        });

        setupNotifications();
    }

    private void setupNotifications() {
        TextView notification1 = findViewById(R.id.notification1);
        TextView notification2 = findViewById(R.id.notification2);
        TextView notification3 = findViewById(R.id.notification3);
        TextView notification4 = findViewById(R.id.notification4);
        TextView notification5 = findViewById(R.id.notification5);

        setupNotificationTextView(notification1, "Cập nhật hệ thống mới", "Hệ thống đã được cập nhật lên phiên bản 2.0", "10 phút trước");
        setupNotificationTextView(notification2, "Thanh toán thành công", "Bạn đã thanh toán thành công cho đơn hàng #12345", "1 giờ trước");
        setupNotificationTextView(notification3, "Khuyến mãi đặc biệt", "Giảm giá 50% cho tất cả các sản phẩm trong tuần này", "3 giờ trước");
        setupNotificationTextView(notification4, "Nhắc nhở lịch hẹn", "Bạn có cuộc hẹn vào ngày mai lúc 10:00", "Hôm qua");
        setupNotificationTextView(notification5, "Chào mừng bạn", "Cảm ơn bạn đã tham gia cùng chúng tôi!", "2 ngày trước");
    }

    private void setupNotificationTextView(TextView textView, String title, String content, String time) {
        String display = title + "\n" + content + "\n" + time;
        textView.setText(display);

        textView.setClickable(true);
        textView.setFocusable(true);

        textView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Chi tiết: " + title, Toast.LENGTH_SHORT).show();
        });
    }


    private void setupNotificationButton(Button button, String title, String message, String time) {
        String buttonText = String.format(
                "<b>%s</b><br/>" +
                        "%s<br/>" +
                        "<small><i>%s</i></small>",
                title, message, time);

        button.setText(android.text.Html.fromHtml(buttonText));
        button.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        button.setPadding(40, 20, 40, 20);

        button.setOnClickListener(v -> {
            Toast.makeText(this, "Đã mở thông báo: " + title, Toast.LENGTH_SHORT).show();
            button.setAlpha(0.7f);
        });
    }

    private void updateNotificationReadStatus() {
        Button[] notifications = {
                findViewById(R.id.notification1),
                findViewById(R.id.notification2),
                findViewById(R.id.notification3),
                findViewById(R.id.notification4),
                findViewById(R.id.notification5)
        };

        for (Button notification : notifications) {
            notification.setAlpha(0.7f);
        }
    }
}