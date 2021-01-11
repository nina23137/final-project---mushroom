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
package aco_circle.demo.y;

import static aco_circle.demo.y.Vertex_Y.Y.*;
import tech.metacontext.ocnhfa.antsomg.impl.StandardGraph;

/**
 *
 * @author Chiao-Ni,Hsu <maple41520@gmail.com>
 */
public class Graph_Y extends StandardGraph<Edge_Y, Vertex_Y> {

    public Graph_Y(double alpha, double beta) {

        super(alpha, beta);
        setFraction_mode(StandardGraph.FractionMode.Power);
    }

    @Override
    public void init_graph() {

        var color1 = Vertex_Y.get(COLOR1);
        var color2 = Vertex_Y.get(COLOR2);
        var color3 = Vertex_Y.get(COLOR3);
        var color4 = Vertex_Y.get(COLOR4);
        var color5 = Vertex_Y.get(COLOR5);

        this.setStart(color1);

        var _1_1 = new Edge_Y(color1, color1, 10.0);
        var _1_2 = new Edge_Y(color1, color2, 10.0);
        var _1_3 = new Edge_Y(color1, color3, 10.0);
        var _1_4 = new Edge_Y(color1, color4, 10.0);
        var _1_5 = new Edge_Y(color1, color5, 10.0);
        var _2_1 = new Edge_Y(color2, color1, 10.0);
        var _2_2 = new Edge_Y(color2, color2, 10.0);
        var _2_3 = new Edge_Y(color2, color3, 10.0);
        var _2_4 = new Edge_Y(color2, color4, 10.0);
        var _2_5 = new Edge_Y(color2, color5, 10.0);
        var _3_1 = new Edge_Y(color3, color1, 10.0);
        var _3_2 = new Edge_Y(color3, color2, 10.0);
        var _3_3 = new Edge_Y(color3, color3, 10.0);
        var _3_4 = new Edge_Y(color3, color4, 10.0);
        var _3_5 = new Edge_Y(color3, color5, 10.0);
        var _4_1 = new Edge_Y(color4, color1, 10.0);
        var _4_2 = new Edge_Y(color4, color2, 10.0);
        var _4_3 = new Edge_Y(color4, color3, 10.0);
        var _4_4 = new Edge_Y(color4, color4, 10.0);
        var _4_5 = new Edge_Y(color4, color5, 10.0);
        var _5_1 = new Edge_Y(color5, color1, 10.0);
        var _5_2 = new Edge_Y(color5, color2, 10.0);
        var _5_3 = new Edge_Y(color5, color3, 10.0);
        var _5_4 = new Edge_Y(color5, color4, 10.0);
        var _5_5 = new Edge_Y(color5, color5, 10.0);

        this.addEdges(_1_1, _1_2, _1_3, _1_4, _1_5,
                _2_1, _2_2, _2_3, _2_4, _2_5,
                _3_1, _3_2, _3_3, _3_4, _3_5,
                _4_1, _4_2, _4_3, _4_4, _4_5,
                _5_1, _5_2, _5_3, _5_4, _5_5
        );
    }

}
