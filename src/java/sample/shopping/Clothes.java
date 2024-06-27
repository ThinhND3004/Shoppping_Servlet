/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

/**
 *
 * @author ACER
 */
public class Clothes {
    private String id;
    private String name;
    private int quanlity;
    private double gia;

    public Clothes(String id, String name, int quanlity, double gia) {
        this.id = id;
        this.name = name;
        this.quanlity = quanlity;
        this.gia = gia;
    }

    public Clothes() {
        this.id = "";
        this.name = "";
        this.quanlity = 0;
        this.gia = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(int quanlity) {
        this.quanlity = quanlity;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }
    
    
}
