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

import java.util.Random;
import processing.core.PVector;

/**
 *
 * @author Chiao-Ni,Hsu <maple41520@gmail.com>
 */
public record DemoAnt(PVector p) {

    public void move() {
        var r = new Random();
        p.set(p.x + r.nextInt(3) - 1, p.y + r.nextInt(3) - 1);
    }
}
