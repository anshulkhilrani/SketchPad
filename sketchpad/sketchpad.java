import java.awt.*;
import java.awt.event.*;

class sketch_pad extends Frame implements ActionListener,WindowListener,MouseListener,MouseMotionListener,ItemListener
{
    String selected_shape =new String("Square");
    String selected_color=new String("Blue");
    boolean Eraser=false;
    int up_L_X,up_L_Y,W,H,sel_x1,sel_y1,sel_x2,sel_y2;
    String [] extras_list={"Clear Canvas","Erase"};
    String[] color_list={"Black","Cyan","Green","Yellow","Red","Blue","Magenta"};
    String [] shape_list={"Line","Rectangle","Square","Circle"};
    public void windowClosing(WindowEvent e){
        System.exit(0);
    }
    public void windowActivated(WindowEvent eve){}
    public void windowOpened(WindowEvent eve){}
    public void windowIconified(WindowEvent eve){}
    public void windowClosed(WindowEvent eve){}
    public void windowDeactivated(WindowEvent eve){}
    public void windowDeiconified(WindowEvent eve){}
    public void mouseMoved(MouseEvent mouses_mov_eve){}
    public void mouseClicked(MouseEvent mouses_click_eve){}
    public void mouseExited(MouseEvent mouses_exit_eve){}
    public void mouseEntered(MouseEvent mouses_enter_eve){}
    public void itemStateChanged(ItemEvent item_state_chng_eve){}
    public sketch_pad(String str){
        super(str);
        addMouseMotionListener(this);
        addWindowListener(this);
        addMouseListener(this);
        setLayout(null);
        set_menu_items();
        setBackground(Color.white);
    }
    public void actionPerformed(ActionEvent action_performed_eve){
        Graphics ga=getGraphics();
        Object s =action_performed_eve.getActionCommand();
        for(int i=0;i!=color_list.length;i++){
            if(s.equals(color_list[i])){
                selected_color=color_list[i];
                return;
            }
        }
        for(int i=0;i!=shape_list.length;i++){
                if(s.equals(shape_list[i])){
                    selected_shape=shape_list[i];
                    return;}
        }
        if(s.equals("Eraser")){
            Eraser=true;
            return;
        }
        else if(s.equals("Clear Canvas")){
            ga.clearRect(0,0,700,700);
            return;
        }
    }
    void choose_color(Graphics ga){
        for (int i=0;i!=color_list.length;i++){
            if(selected_color.equals(color_list[i])){
                switch(i){
                    case 0:ga.setColor(Color.black);break;
                    case 1:ga.setColor(Color.cyan);break;
                    case 2:ga.setColor(Color.green);break;
                    case 3:ga.setColor(Color.yellow);break;
                    case 4:ga.setColor(Color.red);break;
                    case 5:ga.setColor(Color.magenta);break;
                    case 6:ga.setColor(Color.blue);break;
                }
            }
        }
    }
    public void mouseReleased(MouseEvent mouse_reles_eve){
        Graphics ga=getGraphics();
        if(Eraser){
            Eraser=false;
            return;
        }
        choose_color(ga);
        sel_x2=mouse_reles_eve.getX();
        sel_y2=mouse_reles_eve.getY();
        if(selected_shape.equals("Line")){
            ga.drawLine(sel_x1,sel_y1,sel_x2,sel_y2);
        }
        else if(selected_shape.equals("Circle")){
            draw_selected_shape(ga,"Circle");
        }
        else if(selected_shape.equals("Square")){
            draw_selected_shape(ga,"Square");
        }
        else if(selected_shape.equals("Rectangle")){
            draw_selected_shape(ga,"Rectangle");
        }
        ga.setColor(Color.yellow);
        ga.drawString(".", sel_x1, sel_y1);
        ga.setColor(Color.black);
    }
    void draw_selected_shape(Graphics ga,String sel_shape){
        up_L_X=Math.min(sel_x1,sel_x2);
        up_L_Y=Math.min(sel_y1,sel_y2);
        W=Math.abs(sel_x1-sel_x2);
        H=Math.abs(sel_y1-sel_y2);
        if(sel_shape.equals("Square")){
            ga.fillRect(up_L_X,up_L_Y,W,W);
        }
        else if(sel_shape.equals("Rectangle")){
            ga.fillRect(up_L_X,up_L_Y,W,H);
        }
        else if(sel_shape.equals("Circle")){
            ga.fillOval(up_L_X, up_L_Y, W, W);
        }
    }
    public void mouseDragged(MouseEvent mouse_drag_eve){
        Graphics ga=getGraphics();
        sel_x2=mouse_drag_eve.getX();
        sel_y2=mouse_drag_eve.getY();
        if(Eraser){
            ga.setColor(Color.white);
            ga.fillRect(sel_x2,sel_y2,10,10);
        }
    }
    public void mousePressed(MouseEvent mouse_press_eve){
        if(Eraser) return;
        up_L_X=0;up_L_Y=0;W=0;H=0;
        sel_x2=mouse_press_eve.getX();
        sel_y2=mouse_press_eve.getY();
        Graphics ga=getGraphics();
        ga.drawString(".", sel_x1, sel_y1);
    }
    void set_menu_items(){
        MenuBar m= new MenuBar();
        Menu menu_sh=new Menu("Shapes");
        for(int i=0;i!=shape_list.length;i++){
            menu_sh.add(shape_list[i]);
        }
        m.add(menu_sh);
        menu_sh.addActionListener(this);
        Menu menu_col=new Menu("Colors");
        for(int i=0;i!=color_list.length;i++){
            menu_col.add(color_list[i]);
        }
        m.add(menu_col);
        menu_col.addActionListener(this);
        Menu ex=new Menu("Extras");
        for(int i=0;i!=extras_list.length;i++){
            ex.add(extras_list[i]);
        }
        m.add(ex);
        ex.addActionListener(this);
        setMenuBar(m);
    }
}
class sketchpad{
    public static void main(String []args){
        sketch_pad draw_win=new sketch_pad("Sketchpad");
        draw_win.setSize(700,700);
        draw_win.setVisible(true);
    }
}