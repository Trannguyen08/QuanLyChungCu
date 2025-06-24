package java.utc2.apartmentManage.service.implement.user;

import utc2.apartmentManage.model.Notification;
import utc2.apartmentManage.repository.manager.notificationRepository;
import utc2.apartmentManage.view.user.editWindow.NotificationPanel;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class notificationUserIMP {
    private final notificationRepository notiRepo = new notificationRepository();
    private List<Notification> list;

    public void sortlist() {
        list.sort((n1, n2) -> {
            // So sánh ngày giờ: mới hơn lên trước
            int dateCompare = n2.getSentDate().compareTo(n1.getSentDate());
            if (dateCompare != 0) {
                return dateCompare;
            }

            // Nếu ngày giờ bằng nhau, so sánh ID giảm dần
            return Integer.compare(n2.getID(), n1.getID());
        });
    }

    // hàm trả về thời gian gửi của thông báo
    public String getTimeAgo(LocalDateTime sentDate) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(sentDate, now);

        long minutes = duration.toMinutes();
        long hours = duration.toHours();
        long days = duration.toDays();

        if (minutes < 60) {
            return minutes + " phút trước";
        } else if (hours < 24) {
            return hours + " giờ trước";
        } else {
            return days + " ngày trước";
        }
    }


    public void setUpPanel(JPanel wrapperPanel, String object) {
        list = notiRepo.getAllNotificationForUser(object);
        sortlist();
        JPanel rowPanel = null;
        int count = 0;

        // Lặp qua các thông báo để tạo từng panel
        for (int i = 0; i < list.size(); i++) {
            // Mỗi 3 thông báo, tạo hàng mới
            if (count % 3 == 0) {
                rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 20)); // 20px ngang, 10px dọc
                wrapperPanel.add(rowPanel); // Thêm hàng mới vào wrapperPanel
            }

            Notification n = list.get(i);
            NotificationPanel notiPanel = new NotificationPanel(n.getType(), n.getTitle(), n.getMess(), getTimeAgo(n.getSentDate()));
            rowPanel.add(notiPanel); // Thêm NotificationPanel vào hàng

            count++;
        }

        wrapperPanel.revalidate();
        wrapperPanel.repaint();
    }

    public void search(JPanel wrapperPanel, String keyword) {
        wrapperPanel.removeAll();
        keyword = keyword.toLowerCase();

        JPanel rowPanel = null;
        int count = 0;

        for (Notification n : list) {
            if (n.getTitle().toLowerCase().contains(keyword)) {
                if (count % 3 == 0) {
                    rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
                    wrapperPanel.add(rowPanel);
                }

                NotificationPanel notiPanel = new NotificationPanel(n.getType(), n.getTitle(), n.getMess(), getTimeAgo(n.getSentDate()));
                rowPanel.add(notiPanel);
                count++;
            }
        }

        // Hiển thị lại giao diện sau khi thay đổi
        wrapperPanel.revalidate();
        wrapperPanel.repaint();
    }


}