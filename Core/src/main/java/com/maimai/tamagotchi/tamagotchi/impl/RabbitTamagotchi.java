package com.maimai.tamagotchi.tamagotchi.impl;

import com.maimai.tamagotchi.ProgramCore;
import com.maimai.tamagotchi.action.SimpleAction;
import com.maimai.tamagotchi.event.tamagotchi.TamagotchiStatsChangeEvent;
import com.maimai.tamagotchi.tamagotchi.SimpleTamagotchi;
import com.maimai.tamagotchi.tamagotchi.TamagotchiType;
import com.maimai.tamagotchi.utils.MessageUtils;

public class RabbitTamagotchi extends SimpleTamagotchi {

    public RabbitTamagotchi(String name) {
        super(name, TamagotchiType.RABBIT);
    }

    public void registerActions(ProgramCore core) {
        registerAction("pet", new SimpleAction.Builder()
                .createRequirement((player, item) -> {
                    if(item == null) {
                        return true;
                    }
                    MessageUtils.sendMessageFromLang(core, "actions.notRequiresItem");
                    return false;
                })
                .createExecutor((player, item) -> {
                    core.getEventRegister().callEvent(new TamagotchiStatsChangeEvent(player.getTamagotchi()));
                    player.getTamagotchi().getHappiness().increase(40D);
                    player.getMoney().increase(5D);
                    MessageUtils.sendMessageFromLang(core, "tamagotchi.rabbit.pet", player.getTamagotchi().getName());
                }).build());

        registerAction("bath", new SimpleAction.Builder()
                .createRequirement((player, item) -> {
                    if(item == null) {
                        return true;
                    }
                    MessageUtils.sendMessageFromLang(core, "actions.notRequiresItem");
                    return false;
                })
                .createExecutor((player, item) -> {
                    core.getEventRegister().callEvent(new TamagotchiStatsChangeEvent(player.getTamagotchi()));
                    player.getTamagotchi().getHappiness().decrement(20D);
                    player.getTamagotchi().getDirty().decrement(80D);
                    player.getMoney().increase(10D);
                    MessageUtils.sendMessageFromLang(core, "tamagotchi.rabbit.bath", player.getTamagotchi().getName());
                }).build());

        registerAction("exercise", new SimpleAction.Builder()
                .createRequirement((player, item) -> {
                    if(item == null) {
                        if (!(player.getTamagotchi().getFatigue().getValue() <= 40)){
                            return true;
                        } else {
                            MessageUtils.sendMessageFromLang(core, "tamagotchi.canNotDoExercise", player.getTamagotchi().getName());
                            return false;
                        }
                    }
                    MessageUtils.sendMessageFromLang(core, "actions.notRequiresItem");
                    return false;
                })
                .createRequirement((player, item) -> {
                    if (!(player.getTamagotchi().getFatigue().getValue() <= 40)){
                        return true;
                    }else {
                        MessageUtils.sendMessageFromLang(core, "tamagotchi.canNotDoExercise", player.getTamagotchi().getName());
                        return false;
                    }
                })
                .createExecutor((player, item) -> {
                    core.getEventRegister().callEvent(new TamagotchiStatsChangeEvent(player.getTamagotchi()));
                    player.getTamagotchi().getFatigue().decrement(40D);
                    player.getTamagotchi().getHunger().decrement(40D);
                    player.getTamagotchi().getThirst().decrement(40D);
                    player.getTamagotchi().getHappiness().decrement(10D);
                    player.getTamagotchi().getDirty().increase(50D);
                    player.getMoney().increase(15D);
                    MessageUtils.sendMessageFromLang(core, "tamagotchi.rabbit.exercise", player.getTamagotchi().getName());
                }).build());

    }

}
