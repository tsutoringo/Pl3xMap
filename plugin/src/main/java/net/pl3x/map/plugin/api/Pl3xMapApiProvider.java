package net.pl3x.map.plugin.api;

import net.pl3x.map.api.MapWorld;
import net.pl3x.map.api.Pl3xMap;
import net.pl3x.map.api.Registry;
import net.pl3x.map.plugin.Pl3xMapPlugin;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

public final class Pl3xMapApiProvider implements Pl3xMap {

    private final Pl3xMapPlugin plugin;
    private final IconRegistry iconRegistry;

    public Pl3xMapApiProvider(final @NonNull Pl3xMapPlugin plugin) {
        this.plugin = plugin;
        this.iconRegistry = new IconRegistry();
    }

    @Override
    public @NonNull Collection<MapWorld> mapWorlds() {
        return Collections.unmodifiableCollection(this.plugin.worldManager().worlds().values());
    }

    @Override
    public @NonNull Optional<MapWorld> getWorldIfEnabled(final @NonNull World world) {
        return this.plugin.worldManager().getWorldIfEnabled(world).map(w -> w);
    }

    @Override
    public @NonNull Optional<MapWorld> getWorldIfEnabled(final @NonNull UUID uuid) {
        final World world = Bukkit.getWorld(uuid);
        if (world == null) {
            return Optional.empty();
        }
        return this.getWorldIfEnabled(world);
    }

    @Override
    public @NonNull Registry<BufferedImage> iconRegistry() {
        return this.iconRegistry;
    }

    @Override
    public @NonNull PlayerManager playerManager() {
        return plugin.playerManager();
    }

}
