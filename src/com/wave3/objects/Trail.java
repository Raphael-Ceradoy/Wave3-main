package com.wave3.objects;

import java.awt.*;
import com.wave3.gameElement.Handler;

public class Trail extends GameObject{

    private float alpha = 1;
    private Color color;
    private float life;
    //life = 0.001 - 0.1

    public Trail(float x, float y, Color color, int width, int height, float life, Handler handler) {
        super(handler);
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
        this.x = x;
        this.y = y;
        id = ID.TRAIL;
    }
    @Override
    public void tick() {
        if(alpha > life) {
            alpha -= life - 0.00001f;
        }
        else {
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setComposite(makeTransparent(alpha));
        g2d.setColor(color);
        g2d.fillRect((int)x, (int)y, (int)width, (int)height);
        g2d.setComposite(makeTransparent(1));
    }

    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return(AlphaComposite.getInstance(type, alpha));
    }

    @Override
    public void collision(ID id) {

    }
}