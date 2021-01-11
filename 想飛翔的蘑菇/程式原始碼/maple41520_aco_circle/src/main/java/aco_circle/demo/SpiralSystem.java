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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import aco_circle.demo.x.Graph_X;
import aco_circle.demo.x.Vertex_X;
import aco_circle.demo.y.Graph_Y;
import aco_circle.demo.y.Vertex_Y;
import tech.metacontext.ocnhfa.antsomg.impl.StandardEdge;
import tech.metacontext.ocnhfa.antsomg.impl.StandardGraph;
import tech.metacontext.ocnhfa.antsomg.model.AntsOMGSystem;

/**
 *
 * @author Chiao-Ni,Hsu <maple41520@gmail.com>
 */
public class SpiralSystem implements AntsOMGSystem<SpiralAnt> {

    int ant_population;
    Map<String, StandardGraph> graphs;
    List<SpiralAnt> ants;
    double pheromone_deposit = 0.5;
    double explore_chance = 0.1;
    double evaporate_rate = 0.1;
    double alpha = 2, beta = 6;

    public SpiralSystem(int ant_population) {

        this.ant_population = ant_population;
    }

    @Override
    public void init_graphs() {

        this.graphs = Map.of(
                "x", new Graph_X(alpha, beta),
                "y", new Graph_Y(alpha, beta)
        );
    }

    Graph_X getX() {

        return (Graph_X) this.graphs.get("x");
    }

    Graph_Y getY() {

        return (Graph_Y) this.graphs.get("y");
    }

    @Override
    public void init_population() {

        this.ants = Stream.generate(()
                -> new SpiralAnt(
                        getX().getStart(),
                        getY().getStart()))
                .limit(ant_population)
                .collect(Collectors.toList());
    }

    @Override
    public void navigate() {

        this.ants.stream().forEach(ant -> {
            if (!ant.isCompleted()) {
                var trace = ant.getCurrentTrace();
                var x = getX().move((Vertex_X) trace.getDimension("x"),
                        this.pheromone_deposit, this.explore_chance);
                var y = getY().move((Vertex_Y) trace.getDimension("y"),
                        this.pheromone_deposit, this.explore_chance);
                var new_trace = new SpiralTrace(x, y);
                ant.setCurrentTrace(new_trace);
                if (ant.isBalanced()) {
                    ant.addCurrentTraceToRoute();
                    ant.setCompleted(true);
                }
            }
        });
        this.evaporate();
    }

    @Override
    public void evaporate() {

        this.graphs.values().stream()
                .map(StandardGraph::getEdges)
                .flatMap(List<StandardEdge>::stream)
                .forEach(edge -> edge.evaporate(evaporate_rate));

    }

    @Override
    public boolean isAimAchieved() {

        return this.ants.stream().allMatch(SpiralAnt::isCompleted);
    }

    @Override
    public List<SpiralAnt> getAnts() {

        return this.ants;
    }

    @Override
    public Map<String, StandardGraph> getGraphs() {

        return this.graphs;
    }
}
