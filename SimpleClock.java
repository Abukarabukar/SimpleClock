//package SimpleClock;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


public class SimpleClock extends JFrame {
    //1
        Calendar calendar;
        SimpleDateFormat timeFormat;
        SimpleDateFormat dayFormat;
        SimpleDateFormat dateFormat;

        JLabel timeLabel;
        JLabel dayLabel;
        JLabel dateLabel;

        JButton button;
        JButton button1;


        String time;
        String day;
        String date;


        //I need this
        TimeZone timeZone;

        SimpleClock() {



            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Digital Clock");
            this.setLayout(new FlowLayout());
            this.setSize(375, 270);
            this.setResizable(false);

            //And this
            timeZone = TimeZone.getTimeZone("GMT+2");


            timeFormat = new SimpleDateFormat("hh:mm:ss a");
            dayFormat=new SimpleDateFormat("EEEE");
            dateFormat=new SimpleDateFormat("dd MMMMM, yyyy");
            timeLabel = new JLabel();
            timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 59));
            timeLabel.setBackground(Color.BLACK);
            timeLabel.setForeground(Color.WHITE);
            timeLabel.setOpaque(true);
            dayLabel=new JLabel();
            dayLabel.setFont(new Font("Ink Free",Font.BOLD,34));



            //Button to change to 12/24
            button = new JButton("Change Format");
            button.setPreferredSize(new Dimension(150, 80));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (timeFormat.toPattern().equals("hh:mm:ss a")) {
                        timeFormat.applyPattern("HH:mm:ss");
                    } else {
                        timeFormat.applyPattern("hh:mm:ss a");
                    }
                    time = timeFormat.format(Calendar.getInstance().getTime());
                    timeLabel.setText(time);
                }
            });


            //and this inside button
            //timeFormat.setTimeZone(timeZone);
            button1 = new JButton("Change To GMT");
            button1.setPreferredSize(new Dimension(150, 80));

            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (timeFormat.getTimeZone().equals(TimeZone.getDefault())) {
                        timeFormat.setTimeZone(timeZone);
                    } else {
                        timeFormat.setTimeZone(TimeZone.getDefault());

                    }
                    time = timeFormat.format(Calendar.getInstance().getTime());
                    timeLabel.setText(time);



                    //  timeFormat.setTimeZone(timeZone); // Set the time zone for the time format
                    //        time = timeFormat.format(Calendar.getInstance().getTime()); // Format time with the specified time zone
                    //        timeLabel.setText(time);
//                    JOptionPane.showMessageDialog(SimpleClock.this ,"That's Better!");
                }
            });



            dateLabel=new JLabel();
            dateLabel.setFont(new Font("Ink Free",Font.BOLD,30));



            this.add(timeLabel);
            this.add(dayLabel);
            this.add(dateLabel);
            this.add(button1);
            this.add(button);
            this.setVisible(true);

            setTimer();
        }

        public void setTimer() {
            while (Thread.currentThread().isAlive()) {
                time = timeFormat.format(Calendar.getInstance().getTime());
                timeLabel.setText(time);

                day = dayFormat.format(Calendar.getInstance().getTime());
                dayLabel.setText(day);

                date = dateFormat.format(Calendar.getInstance().getTime());
                dateLabel.setText(date);

                try {
                    System.out.println("here 1");
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

            }
        }
        public static void main(String[] args) {


          new SimpleClock();

        }
    }
