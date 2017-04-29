package com.mcrmb.sponge.hook;

import com.mcrmb.sponge.McrmbCoreAPI;
import com.mcrmb.sponge.result.CheckMoneyResult;
import me.rojo8399.placeholderapi.PlaceholderAPIPlugin;
import me.rojo8399.placeholderapi.PlaceholderService;
import me.rojo8399.placeholderapi.expansions.Expansion;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by txgs888 on 2017/4/28.
 */
public class PlaceholderExpansion implements Expansion {
    public static void register() {
        PlaceholderService service = (PlaceholderService) PlaceholderAPIPlugin.getInstance().getGame().getServiceManager().provideUnchecked(PlaceholderService.class);
        service.registerPlaceholder(new PlaceholderExpansion());
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getIdentifier() {
        return "mcrmb";
    }

    @Override
    public String getAuthor() {
        return "mcrmb.com";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public List<String> getSupportedTokens() {
        return new ArrayList<>();
    }

    @Override
    public Text onPlaceholderRequest(Player player, Optional<String> optional) {
        CheckMoneyResult result = McrmbCoreAPI.checkMoneyByCache(player.getName());
        if (result == null) {
            return Text.of("-1");
        }
        return Text.of(result.getMoney());
    }
}
