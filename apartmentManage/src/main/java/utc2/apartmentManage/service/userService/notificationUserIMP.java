package main.java.utc2.apartmentManage.service.userService;

import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JPanel;
import main.java.utc2.apartmentManage.model.Notification;
import main.java.utc2.apartmentManage.repository.ManagerRepository.notificationRepository;
import main.java.utc2.apartmentManage.view.UserUI.editWindow.NotificationPanel;

public class notificationUserIMP {
    private final notificationRepository notiRepo = new notificationRepository();
    private List<Notification> list;
    
    public void sortlist() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        list.sort((n1, n2) -> {
            LocalDate d1 = LocalDate.parse(n1.getSentDate(), formatter);
            LocalDate d2 = LocalDate.parse(n2.getSentDate(), formatter);

            int dateCompare = d2.compareTo(d1);
            if (dateCompare != 0) {
                return dateCompare;
            }

            return Integer.compare(n2.getID(), n1.getID());
        });
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
                rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20)); // 20px ngang, 10px dọc
                wrapperPanel.add(rowPanel); // Thêm hàng mới vào wrapperPanel
            }

            Notification n = list.get(i);
            NotificationPanel notiPanel = new NotificationPanel(n.getType(), n.getTitle(), n.getMess(), n.getSentDate());
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

                NotificationPanel notiPanel = new NotificationPanel(n.getType(), n.getTitle(), n.getMess(), n.getSentDate());
                rowPanel.add(notiPanel);
                count++;
            }
        }

        // Hiển thị lại giao diện sau khi thay đổi
        wrapperPanel.revalidate();
        wrapperPanel.repaint();
    }





}
