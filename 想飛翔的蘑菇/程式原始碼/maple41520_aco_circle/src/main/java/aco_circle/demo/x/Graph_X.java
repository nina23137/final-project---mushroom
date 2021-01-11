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
package aco_circle.demo.x;

import aco_circle.demo.x.Vertex_X.*;
import tech.metacontext.ocnhfa.antsomg.impl.StandardGraph;

/**
 *
 * @author Chiao-Ni,Hsu <maple41520@gmail.com>
 */
public class Graph_X extends StandardGraph<Edge_X, Vertex_X> {

    public Graph_X(double alpha, double beta) {

        super(alpha, beta);
        setFraction_mode(StandardGraph.FractionMode.Power);
    }

    @Override
    public void init_graph() {

        var stay = Vertex_X.get(X.STAY);
        var in = Vertex_X.get(X.IN);
        var out = Vertex_X.get(X.OUT);

        var stayout = 1.0;
        var stayin = 10.0;

        this.setStart(stay);
        var S_I = new Edge_X(stay, in, stayin);
        var I_S = new Edge_X(in, stay, stayout);
        var S_O = new Edge_X(stay, out, stayout);
        var O_S = new Edge_X(out, stay, stayin);
        var O_I = new Edge_X(out, in, 20.0);
        var I_O = new Edge_X(in, out, 5.0);
        var I_I = new Edge_X(in, in, 10.0);
        var O_O = new Edge_X(out, out, 1.0);
        var S_S = new Edge_X(stay, stay, 50.0);
        this.addEdges(
                S_I, I_S,
                S_O, O_S, O_I,
                I_O, O_O, I_I,
                 S_S
        );
    }
}
