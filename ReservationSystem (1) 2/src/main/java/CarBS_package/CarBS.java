/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CarBS_package;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import reserve.login.Login;

/**
 *
 * @author mskim
 */
public class CarBS extends JPanel {
    
    private CarBS_information carInfo;
    private JTextField car_company;
    private JComboBox rental_time;
    private JTextField car_cost;
    private JComboBox vehicle_type;
    private JRadioButton gasoline;
    private JRadioButton diesel;
    private JRadioButton electricity;
    private JRadioButton high_pass_yes;
    private JRadioButton high_pass_no;
    private JTextField register;
    
    private JComboBox search_combo;
    private JTextField search_text;
    private JButton searchBT;
    private DefaultTableModel car_model;
    private JTable car_table;
    private JScrollPane car_scrollpane;
    private String file;
    private String data;
    private String search_combo_text;
    private String text;
    private int row;
    private int lineCount;
    
    
    public CarBS(){
        super(new BorderLayout());
        
        car_company = new JTextField(20);
        String[]rental_time_combo={"::대여 시간::","12시간","24시간","30시간","36시간","42시간","48시간"};
        rental_time = new JComboBox<>(rental_time_combo);
        car_cost = new JTextField(10);
        String[]vehicle_type_combo = {"::차종::","모닝","레이","아반떼","쏘나타","그랜저","쏘렌토","스타리아","아이오닉","코나","레이"};
        vehicle_type = new JComboBox<>(vehicle_type_combo);
        gasoline = new JRadioButton("휘발유");
        diesel = new JRadioButton("경유");
        electricity = new JRadioButton("전기");
        high_pass_yes = new JRadioButton("O");
        high_pass_no = new JRadioButton("X");
        register = new JTextField(2);
        
        carInfo = new CarBS_information( car_company.getText(),
                (String)rental_time.getSelectedItem(),
                car_cost.getText(),
                (String)vehicle_type.getSelectedItem(),
                gasoline.isSelected() ? "휘발유" : "",
                diesel.isSelected() ? "경유" : "",
                electricity.isSelected() ? "전기" : "",
                high_pass_yes.isSelected() ? "O" : "X",
                high_pass_no.isSelected() ? "X" : "O",
                register.getText()
                
        );
        
        file = "car_business_textfile.txt";
        
              
        rentalcar_list();
    }
    
    private void rentalcar_list(){
        
        setLayout(null);
        
        // 이미지를 표시할 JLabel//
        JLabel hotel_image_label = new JLabel();
        ImageIcon hotel_list_Icon = new ImageIcon("car_list.png");
        hotel_image_label.setIcon(hotel_list_Icon);
        hotel_image_label.setBounds(18,1,80,80);
        add(hotel_image_label);
        
        // 상단 패널 //
        JPanel input_panel = new JPanel();
        input_panel.setBorder(new TitledBorder(new LineBorder(Color.gray,1),"입력"));
        input_panel.setLayout(new GridLayout(4,4,10,15));
    
        
        input_panel.add(new JLabel("자동차 회사"));
        car_company.setText(carInfo.getCarCompany());
        input_panel.add(car_company);
        
        input_panel.add(new JLabel("대여 시간"));
        JPanel rental_time_Panel = new JPanel();
        rental_time_Panel.setLayout(new GridLayout(1, 2));
        rental_time.setSelectedItem(carInfo.getRentalTime());
        rental_time_Panel.add(rental_time);
        input_panel.add(rental_time_Panel);
        
        
        input_panel.add(new JLabel("비용"));
        JPanel car_costPanel = new JPanel();
        car_costPanel.setLayout(new GridLayout(1, 2));
        car_cost.setText(carInfo.getCarCost());
        car_costPanel.add(car_cost);
        car_costPanel.add(new JLabel(" 원"));
        input_panel.add(car_costPanel);
        
        input_panel.add(new JLabel("차종"));
        JPanel vehicle_type_Panel = new JPanel();
        vehicle_type_Panel.setLayout(new GridLayout(1, 2));
        vehicle_type.setSelectedItem(carInfo.getVehicleType());
        input_panel.add(vehicle_type);
        
        // 모닝, 레이, 아반떼, 쏘나타, 그랜저 : 휘발유
        // 쏘렌토, 스타리아 : 휘발유
        // 아이오닉, 코나, 레이 : 전기
        
        input_panel.add(new JLabel("연료"));
        JPanel oil_Panel = new JPanel();
        oil_Panel.setLayout(new GridLayout(1,3));
        ButtonGroup oil_Group = new ButtonGroup();
        
        
        oil_Group.add(gasoline);
        oil_Group.add(diesel);
        oil_Group.add(electricity);
        if("휘발유".equals(carInfo.getGasoline())){
            gasoline.setSelected(true);
        }
        else if("경유".equals(carInfo.getDiesel())){
            diesel.setSelected(true);
        }
        else if("전기".equals(carInfo.getElectricity())){
            electricity.setSelected(true);
        }

        
        oil_Panel.add(gasoline);
        oil_Panel.add(diesel);
        oil_Panel.add(electricity);
        
        input_panel.add(oil_Panel);
        
        
        input_panel.add(new JLabel("하이패스")); // 하이패스 유무
        JPanel high_pass_Panel = new JPanel();
        high_pass_Panel.setLayout(new GridLayout(1,2));
        ButtonGroup high_pass_Group = new ButtonGroup();
   
        high_pass_Group.add(high_pass_yes);
        high_pass_Group.add(high_pass_no);
        
        high_pass_Group.add(high_pass_yes);
        high_pass_Group.add(high_pass_no);
        if ("O".equals(carInfo.getYes())){
           high_pass_yes.setSelected(true);
        }
        else if("X".equals(carInfo.getNo())){
            high_pass_no.setSelected(true);
        }
        
        high_pass_Panel.add(high_pass_yes);
        high_pass_Panel.add(high_pass_no);
        
        input_panel.add(high_pass_Panel);
        
        
        input_panel.add(new JLabel("등록 여부"));
        register.setText(carInfo.getCarRegister());
        input_panel.add(register);
        
        // 사진 등록
        input_panel.add(new JLabel("렌터카 대표 사진"));
        JButton btnOpen = new JButton("사진 등록");
        input_panel.add(btnOpen);
        
        btnOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog file_open = new FileDialog((JFrame) SwingUtilities.getWindowAncestor(CarBS.this), "사진 등록", FileDialog.LOAD);
                file_open.setVisible(true);
                
                String path = file_open.getDirectory();
            }
        });
        
        
        input_panel.setBounds(15,75,655,300);
        add(input_panel);
        
        
        // 검색 패널 //
        JPanel search_panel = new JPanel();
        
        // 콤보 박스
        String [] search = {"전체 조회","비즈니스 넘버", "자동차 회사"};
        search_combo = new JComboBox(search);
        search_panel.add(search_combo);
        
        // 검색 텍스트 필드
        search_text = new JTextField(30);
        search_panel.add(search_text);
        
        // 조회 버튼
        searchBT = new JButton("조회");
        search_panel.add(searchBT);
        
        search_panel.setBounds(15,400,655,50);
        add(search_panel);
        
        
        
        
        // 센터 패널 //
        JPanel car_list_panel = new JPanel(new BorderLayout());
        String header[] = {"비즈니스 넘버", "자동차 회사", "대여시간","비용","차종","연료","하이패스","등록 여부"};
        String contents[][]={};
        
        car_model = new DefaultTableModel(contents, header);
        car_table = new JTable(car_model);
        car_scrollpane = new JScrollPane(car_table);
        car_list_panel.add(car_scrollpane, BorderLayout.CENTER);

        car_list_panel.setBounds(15,450,655,230);
        add(car_list_panel);
        
         // 하단 패널
        JPanel south_panel = new JPanel();
        south_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 7, 3));
       
        JButton backBT = new JButton("이전");
        JButton modifyBT = new JButton("수정");
        JButton addBT = new JButton("추가");
        JButton deleteBT = new JButton("삭제");
        JButton okBT = new JButton("확인");

        
        south_panel.add(backBT);
        south_panel.add(modifyBT);
        south_panel.add(addBT);
        south_panel.add(deleteBT);
        south_panel.add(okBT);
        
        
        south_panel.setBounds(150,690,400,50);
        add(south_panel);
        
        backBT.addActionListener(new ActionListener(){ // 추가 버튼 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                back(car_table);
            }
            
        });
        
        
        addBT.addActionListener(new ActionListener(){ // 추가 버튼 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                add(car_table);
            }
            
        });
        
        searchBT.addActionListener(new ActionListener(){ // 조회 버튼 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                search(car_table);
            }
        });
        
        modifyBT.addActionListener(new ActionListener(){ // 수정버튼 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                modify(car_table);
            }
        });
        
        deleteBT.addActionListener(new ActionListener(){ // 삭제 버튼 눌렀을 때
            @Override
            public void actionPerformed(ActionEvent e) {
                delete(car_table);
            }
            
        });
        
        okBT.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ok(car_table);
            }
            
        });
        
        
        
        
        
        
        
    }
    
    private void back(JTable hotel_table){
        
        
        // 현재 패널이 포함된 프레임 찾기 및 닫기
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (topFrame != null) {
            topFrame.dispose();
        }

        Login click = new Login();
        click.setVisible(true);

    }
    

    private void add(JTable car_table){
        try{
            

            
            String car_company_text = car_company.getText();
            String rental_time_text =(String) rental_time.getSelectedItem();
            String car_cost_text = car_cost.getText();
            String vehicle_type_text = (String)vehicle_type.getSelectedItem();
            String oil ="";
            if(gasoline.isSelected()){
                oil="휘발유";
            }
            else if(diesel.isSelected()){
                oil="경유";
            }
            else if(electricity.isSelected()){
                oil="전기";
            }
            
            String high_pass = "";
            if(high_pass_yes.isSelected()){
                high_pass="O";
            }
            else if(high_pass_no.isSelected()){
                high_pass="X";
            }
            
            String car_registration = register.getText();
            
            if (car_company_text.isEmpty() || rental_time_text.isEmpty() || car_cost_text.isEmpty() || vehicle_type_text.isEmpty() || oil.isEmpty() || high_pass.isEmpty() || car_registration.isEmpty()){
                JOptionPane.showMessageDialog(this, "모든 필드를 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            car_model = (DefaultTableModel) car_table.getModel();
            lineCount = car_model.getRowCount()+1;
            car_model.addRow(new Object[]{String.valueOf(lineCount),car_company_text,rental_time_text,car_cost_text,vehicle_type_text,oil,high_pass,car_registration});
            
            
            data = lineCount + "|" + car_company_text + "|" + rental_time_text + "|" +car_cost_text + "|" + vehicle_type_text + "|" + oil + "|" + high_pass + "|" + car_registration;
                   
            // 파일에 데이터 추가하기
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(data+"\n");
            writer.close();
            
            
            
            

            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void search(JTable car_table){
        search_combo_text = (String)search_combo.getSelectedItem();
        text = search_text.getText();
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            DefaultTableModel model = (DefaultTableModel) car_table.getModel();
            model.setRowCount(0); // 테이블 초기화
            
            while((line=reader.readLine())!=null){
                String[] data = line.split("\\|");
                
                if(search_combo_text == null){ // 검색 콤보 상자가 선택되지 않았을 경우 전체 조회로 처리
                    model.addRow(data);
                }
                else if(search_combo_text.equals("전체 조회")){
                    model.addRow(data);
                }
                else if (search_combo_text.equals("비즈니스 넘버") && data[0].equals(text)) {
                    model.addRow(data); 
                }
                else if (search_combo_text.equals("자동차 회사") && data[1].equals(text)) {
                    model.addRow(data);
                }
            }
            
            reader.close();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    
    private void modify(JTable car_table){
        /*
        row = car_table.getSelectedRow();
        
        String new_breakfast = "";
        if (breakfast_yes.isSelected()) {
            new_breakfast = "O";
        } else if (breakfast_no.isSelected()) {
            new_breakfast = "X";
        }
        
        String new_roomtype = "";
        if (suit_room.isSelected()) {
            new_roomtype = "스위트룸";
        } else if (deluxe_room.isSelected()) {
            new_roomtype = "디럭스룸";
        } else if (standard_room.isSelected()) {
            new_roomtype = "스탠다드룸";
        }

                
        if(row!=-1){ // 만약 선택된 행이 있다면
            String[] new_hotel_input={ // 각각의 새로운(수정돤) 정보를 배열로 저장
                hotel_name.getText(),
                (String) hotel_area.getSelectedItem(),
                detailed_address.getText(),
                (String) num_guest.getSelectedItem(),
                new_breakfast,
                new_roomtype,
                hotel_cost.getText(),
                hotel_register.getText()
            };
         
            DefaultTableModel model = (DefaultTableModel) hotel_table.getModel();
            for(int i=0; i<new_hotel_input.length; i++){
                if(new_hotel_input[i].isEmpty()){ // 수정된 정보가 없으면
                    new_hotel_input[i] = (String) hotel_model.getValueAt(row,i+1); // 현재 값을 유지 (get을 쓴거임)
                }
                hotel_model.setValueAt(new_hotel_input[i],row,i+1); 
            }
        }
*/
    }
    
    private void delete(JTable car_table){
        row=car_table.getSelectedRow();
        car_model.removeRow(row);
        
        for(int i=row; i<car_model.getRowCount(); i++){
            car_model.setValueAt(i+1, i, 0);
        }

    }
    
    private void ok(JTable car_table){
        JOptionPane.showMessageDialog(this, "완료되었습니다.", "알림", JOptionPane.ERROR_MESSAGE);
        Window window = SwingUtilities.getWindowAncestor(this);
        window.dispose();
    }

  
}

 


    

