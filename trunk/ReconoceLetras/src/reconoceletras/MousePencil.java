package reconoceletras;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MousePencil implements MouseMotionListener {
        private DrawingPanel v;
	private boolean isDrawing;
	
        public MousePencil(DrawingPanel v) {
                v.addMouseMotionListener(this);
		this.v = v;
		isDrawing = true;
        }
	
	private Drawing d() {return v.getDrawing();}
	
	public boolean isDrawing() {
		return isDrawing;
	}
	
	public void draw() {
		isDrawing = true;
	}
	
	public void erase() {
		isDrawing = false;
	}
	
    @Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX() / v.xCell();
		int y = e.getY() / v.yCell();
		d().set(x, y, isDrawing());
		v.repaint();
	}

    @Override
	public void mouseMoved(MouseEvent e) {}
}
