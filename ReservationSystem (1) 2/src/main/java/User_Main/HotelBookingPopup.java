package User_Main;

import java.awt.Component;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;

class BookingPopup {
    private JPanel formPanel;
    private JScrollPane scrollPane;
    private FormState currentState;

    public BookingPopup() {
        // 상태는 나중에 setState 메소드를 통해 설정됩니다.
    }

    public BookingPopup(FormState initialState) {
        this.currentState = initialState;
        openBookingPage();
    }

    public void setState(FormState state) {
        this.currentState = state;
        openBookingPage();
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setFormPanel(JPanel formPanel) {
        this.formPanel = formPanel;
    }

    public void addScrollPane(JPanel formPanel) {
        scrollPane = new JScrollPane(formPanel);
        scrollPane.setBounds(0, 0, 700, 800);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void completeReservation(JPanel build) {
        String reservationNumber = JavaNaver.generateReservationNumber(); // Generate reservation number
        List<String> reservationDetails = getReservationDetails(formPanel, reservationNumber);

        // 디버깅을 위한 예약 세부 정보 출력
        for (String detail : reservationDetails) {
            System.out.println(detail);
        }

        // 원하는 형식의 문자열로 변환하여 파일에 저장
        String formattedDetails = formatReservationDetails(reservationDetails);
        saveReservationDetailsToFile(formattedDetails);

        // Show confirmation dialog
        int response = JOptionPane.showOptionDialog(
                formPanel,
                "예약 완료되었습니다. 예약 번호: " + reservationNumber,
                "예약 완료",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{"확인"},
                "확인"
        );
    }

    public void goBack() {
        JavaNaver.goBack();
    }

    private List<String> getReservationDetails(JPanel formPanel, String reservationNumber) {
        Component[] components = formPanel.getComponents();
        List<String> reservationDetails = new ArrayList<>();

        reservationDetails.add(reservationNumber); // 예약 번호

        // 현재 날짜를 추가합니다.
        String currentDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
        reservationDetails.add(currentDate); // 현재 날짜

        reservationDetails.add("홍길동"); // 고객 이름 (임시)
        reservationDetails.add("123456"); // 고객 ID (임시)

        boolean saveHotel = false, saveAirline = false, saveCar = false;
        for (Component component : components) {
            if (component instanceof JRadioButton) {
                JRadioButton radioButton = (JRadioButton) component;
                String name = radioButton.getName();
                if (name != null) {
                    if (name.equals("hotelRegistrationYes") && radioButton.isSelected()) {
                        saveHotel = true;
                    }
                    if (name.equals("airlineRegistrationYes") && radioButton.isSelected()) {
                        saveAirline = true;
                    }
                    if (name.equals("carRegistrationYes") && radioButton.isSelected()) {
                        saveCar = true;
                    }
                }
            }
        }

        for (Component component : components) {
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                String name = textField.getName();
                if (name != null) {
                    if (saveHotel && name.startsWith("hotel")) {
                        reservationDetails.add(textField.getText());
                    } else if (saveAirline && name.startsWith("airline")) {
                        reservationDetails.add(textField.getText());
                    } else if (saveCar && name.startsWith("car")) {
                        reservationDetails.add(textField.getText());
                    } else {
                        reservationDetails.add("");
                    }
                }
            } else if (component instanceof JComboBox) {
                JComboBox<String> comboBox = (JComboBox<String>) component;
                String name = comboBox.getName();
                if (name != null) {
                    if (saveHotel && name.startsWith("hotel")) {
                        reservationDetails.add(comboBox.getSelectedItem().toString());
                    } else if (saveAirline && name.startsWith("airline")) {
                        reservationDetails.add(comboBox.getSelectedItem().toString());
                    } else if (saveCar && name.startsWith("car")) {
                        reservationDetails.add(comboBox.getSelectedItem().toString());
                    } else {
                        reservationDetails.add("");
                    }
                }
            } else if (component instanceof JRadioButton) {
                JRadioButton radioButton = (JRadioButton) component;
                String name = radioButton.getName();
                if (name != null) {
                    if (saveHotel && name.startsWith("hotel") && radioButton.isSelected()) {
                        reservationDetails.add(radioButton.getText());
                    } else if (saveAirline && name.startsWith("airline") && radioButton.isSelected()) {
                        reservationDetails.add(radioButton.getText());
                    } else if (saveCar && name.startsWith("car") && radioButton.isSelected()) {
                        reservationDetails.add(radioButton.getText());
                    } else {
                        reservationDetails.add("");
                    }
                }
            }
        }

        return reservationDetails;
    }

    private String formatReservationDetails(List<String> reservationDetails) {
        return String.join("/", reservationDetails);
    }

    private void saveReservationDetailsToFile(String reservationDetails) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reservation_details.txt", true))) {
            writer.write(reservationDetails);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openBookingPage() {
        currentState.handle(this);
    }
}
