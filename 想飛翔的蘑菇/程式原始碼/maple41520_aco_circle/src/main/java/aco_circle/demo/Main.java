/*
 * Copyright 2021 Chiao-Ni,Hsu <maple41520@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package aco_circle.demo;

import java.awt.Color;
import java.util.Map;
import java.util.stream.Collectors;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.geom.*;

import java.util.Random;

/**
 *
 * @author Chiao-Ni,Hsu <maple41520@gmail.com>
 */
public class Main extends PApplet {

    int size1 = 1920;
    int size2 = 1080;
    SpiralSystem demo;
    int aa = 250;  //圓圈
    int bb = 80;

    float theta;
    Map<SpiralAnt, Float> radius;
    Map<SpiralAnt, PVector> vector;
    Ellipse2D inner_arc, outer_arc;

    
    java.util.List<DemoAnt> ants;
    float move_amount = 10f;
    float delta_theta = 23.5f;
    Random rand = new Random();
    float x = 0f;
    float y = 0f;

    @Override
    public void settings() {
        size(size1, size2);
        //fullScreen();

    }

    @Override
    public void setup() {

        colorMode(RGB);
        background(0);
        noFill();
        Random rand = new Random();
        demo = new SpiralSystem(80);
        demo.init_graphs();
        demo.init_population();

        this.outer_arc = new Ellipse2D.Double(size1 / 2 - this.aa, size2 / 2 - this.aa, this.aa * 2, this.aa * 2);
        this.inner_arc = new Ellipse2D.Double(size1 / 2 - this.aa + bb, size2 / 2 - this.aa + bb, 2 * (this.aa - bb), 2 * (this.aa - bb));

        while (!inSquare(this.x, this.y)) {
            this.x = rand.nextInt(size1);
            this.y = rand.nextInt(size2);
        }
        colorMode(RGB);
        background(0);
                radius = demo.ants.stream()
                .collect(Collectors.toMap(ant -> ant, ant -> 0f));

    }

    public boolean inSquare(float x, float y) {

        return inner_arc.contains(x, y) || !outer_arc.contains(x, y);
    }

    public void SetOuterArc() {
        this.outer_arc = new Ellipse2D.Double(size1 / 2 - this.aa, size2 / 2 - this.aa, this.aa * 2, this.aa * 2);
    }

    public void SetInnerArc() {
        this.inner_arc = new Ellipse2D.Double(size1 / 2 - this.aa + 100, size2 / 2 - this.aa + 100, 2 * (this.aa - 100), 2 * (this.aa - 100));
    }

    @Override
    public void draw() {
        background(0);

        Random rand = new Random();
        stroke(50);
        
       // VariableRes = serial.read();

        demo.ants.stream()
                .forEach(ant -> {

                    this.aa = 150 + rand.nextInt(20) * 10;
                    this.bb = rand.nextInt(2) * 30 + 150;

                    var move = ant.getCurrentTrace().getDimension("x");

                    if (!mousePressed) {
                        float r = 2;
                        if ("IN".equals(move.getName())) {
                            r -= 3;
                        }
                        if ("OUT".equals(move.getName())) {
                            r += 3;
                        }
                        radius.replace(ant, r);

                        float x = 100;
                        float y = 100;

                        while (inSquare(x, y)) {
                            x = rand.nextInt(size1);
                            y = rand.nextInt(size2);
                        }

                        x += r * cos(this.theta + (31 / 10));
                        y += r * sin(this.theta);

                        switch (ant.currentTrace.getDimension("y").getName()) {
                            case "COLOR1" -> {
                                stroke(new Color(241, 210, 210).getRGB());
                            }
                            case "COLOR2" -> {
                                stroke(new Color(215, 130, 131).getRGB());
                            }
                            case "COLOR3" -> {
                                stroke(new Color(241, 220, 149).getRGB());
                            }
                            case "COLOR4" -> {
                                stroke(new Color(156, 120, 120).getRGB());
                            }
                            case "COLOR5" -> {
                                stroke(new Color(129, 152, 200).getRGB());
                            }

                        }

                        strokeWeight(3 / 2);
                        beginShape();
                        curveVertex(x, y);
                        curveVertex(x + r * cos(this.theta + PI), y + r * sin(this.theta + PI));
                        curveVertex(x + 5 * r * cos(this.theta + PI + PI), y + 5 * r * sin(this.theta + PI + PI));
                        curveVertex(x + r * cos(this.theta + PI + PI + PI), y + r * sin(this.theta + PI + PI + PI));
                        curveVertex(x + r * cos(this.theta + PI + PI + PI), y + r * sin(this.theta + PI + PI + PI));

                        endShape();
                        noFill();
                    }

                    if (mousePressed) {
                        var r = this.theta * PI / 45;
                        if ("IN".equals(move.getName())) {
                            r -= 15;
                        }
                        if ("OUT".equals(move.getName())) {
                            r += 15;
                        }
                        radius.replace(ant, r);

                        float x = 100;
                        float y = 100;

                        while (inSquare(x, y)) {
                            x = rand.nextInt(size1);
                            y = rand.nextInt(size2);
                        }

                        x += r * cos(this.theta + (31 / 10));
                        y += r * sin(this.theta);

                        switch (ant.currentTrace.getDimension("y").getName()) {
                            case "COLOR1" -> {
                                stroke(new Color(241, 210, 210).getRGB());
                                fill(new Color(241, 210, 210).getRGB(), 100);
                            }
                            case "COLOR2" -> {
                                stroke(new Color(215, 130, 131).getRGB());
                                fill(new Color(215, 130, 131).getRGB(), 100);
                            }
                            case "COLOR3" -> {
                                stroke(new Color(241, 220, 149).getRGB());
                                fill(new Color(241, 220, 149).getRGB(), 100);
                            }
                            case "COLOR4" -> {
                                stroke(new Color(156, 120, 120).getRGB());
                                fill(new Color(156, 120, 120).getRGB(), 100);
                            }
                            case "COLOR5" -> {
                                stroke(new Color(129, 152, 200).getRGB());
                                fill(new Color(129, 152, 200).getRGB(), 100);
                            }
                        }

                        strokeWeight(3 / 2);
                        var cc = random(0, 7);
                        if (cc > 2) {
                            rect(x, y, random(40, 60), random(40, 60));
                        }
                        noFill();
                    }

                });

        this.theta += delta_theta * PI / 6000;
        if (demo.isAimAchieved()) {
            setup();
        } else {
            demo.navigate();
        }

    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0");
        PApplet.main(Main.class);
    }
}
