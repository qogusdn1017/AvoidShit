/*
 * Copyright (c) 2021 BaeHyeonWoo
 *
 *  Licensed under the General Public License, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/gpl-3.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.baehyeonwoo.avoidshit

import org.bukkit.Bukkit
import org.bukkit.Color
import org.bukkit.GameMode
import org.bukkit.Particle
import org.bukkit.Particle.DustOptions
import org.bukkit.plugin.java.JavaPlugin

/***
 * @author BaeHyeonWoo
 */

class ShitMain : JavaPlugin() {

    companion object {
        lateinit var instance: ShitMain
            private set
    }

    override fun onEnable() {
        saveDefaultConfig()
        val dustOptions = DustOptions(Color.AQUA, 1.0F)
        instance = this
        server.pluginManager.registerEvents(ShitEvent(), this)
        server.scheduler.scheduleSyncRepeatingTask(this, {
           Bukkit.getOnlinePlayers().forEach {
               if (it.gameMode == GameMode.SPECTATOR) {
                   it.world.spawnParticle(Particle.REDSTONE, it.location.add(0.0, 2.0, 0.0), 2, dustOptions)
               }
           }
        }, 0, 0)
        ShitKommand.shitKommand()
    }
}