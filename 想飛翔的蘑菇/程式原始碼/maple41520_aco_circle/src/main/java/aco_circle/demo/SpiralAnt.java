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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import aco_circle.demo.x.Edge_X;
import aco_circle.demo.x.Vertex_X;
import aco_circle.demo.y.Edge_Y;
import aco_circle.demo.y.Vertex_Y;
import tech.metacontext.ocnhfa.antsomg.impl.StandardMove;
import tech.metacontext.ocnhfa.antsomg.model.Ant;
import tech.metacontext.ocnhfa.antsomg.model.Vertex;

/**
 *
 * @author Chiao-Ni,Hsu <maple41520@gmail.com>
 */
public class SpiralAnt implements Ant<SpiralTrace> {

    SpiralTrace currentTrace;
    List<SpiralTrace> route;
    private boolean completed;

    public SpiralAnt(Vertex_X x, Vertex_Y y) {

        this.currentTrace = new SpiralTrace(
                new StandardMove<>(new Edge_X(x)),
                new StandardMove<>(new Edge_Y(y))
        );
        this.route = new ArrayList<>();
    }

    boolean isBalanced() {

        var count = route.stream()
                .map(trace -> trace.getDimension("x"))
                .collect(Collectors.groupingBy(Vertex::getName,
                        Collectors.counting()));
        if (count.size() == 3) {     //==3
            if (count.get("OUT") + count.get("IN") + count.get("STAY") > 6) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<SpiralTrace> getRoute() {

        return this.route;
    }

    @Override
    public void addCurrentTraceToRoute() {

        this.route.add(this.currentTrace);
    }

    @Override
    public SpiralTrace getCurrentTrace() {

        return this.currentTrace;
    }

    @Override
    public void setCurrentTrace(SpiralTrace trace) {

        if (Objects.nonNull(this.currentTrace)) {
            this.addCurrentTraceToRoute();
        }
        this.currentTrace = trace;
    }

    public boolean isCompleted() {

        return completed;
    }

    public void setCompleted(boolean completed) {

        this.completed = completed;
    }
}
