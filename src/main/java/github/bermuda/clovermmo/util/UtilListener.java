//package github.bermuda.clovermmo.util;
//
//import github.bermuda.clovermmo.CloverMMO;
//import org.bukkit.Material;
//import org.bukkit.OfflinePlayer;
//import org.bukkit.block.Block;
//import org.bukkit.enchantments.Enchantment;
//import org.bukkit.entity.*;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.EventPriority;
//import org.bukkit.event.Listener;
//import org.bukkit.event.entity.CreatureSpawnEvent;
//import org.bukkit.event.entity.EntityChangeBlockEvent;
//import org.bukkit.event.entity.EntityDamageByEntityEvent;
//import org.bukkit.event.entity.EntityDamageEvent;
//import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
//import org.bukkit.event.entity.EntityDamageEvent.DamageModifier;
//import org.bukkit.event.entity.EntityDeathEvent;
//import org.bukkit.event.entity.EntityExplodeEvent;
//import org.bukkit.event.entity.EntityShootBowEvent;
//import org.bukkit.event.entity.EntityTameEvent;
//import org.bukkit.event.entity.EntityTargetEvent;
//import org.bukkit.event.entity.ExplosionPrimeEvent;
//import org.bukkit.event.entity.FoodLevelChangeEvent;
//import org.bukkit.event.entity.PigZapEvent;
//import org.bukkit.event.entity.PotionSplashEvent;
//import org.bukkit.event.entity.ProjectileLaunchEvent;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.PotionMeta;
//import org.bukkit.metadata.FixedMetadataValue;
//import org.bukkit.potion.PotionEffect;
//import org.bukkit.potion.PotionEffectType;
//import org.bukkit.projectiles.ProjectileSource;
//
//public class UtilListener implements Listener {
//    private final CloverMMO plugin;
//
//    public UtilListener(final CloverMMO plugin) {
//        this.plugin = plugin;
//    }
//
//    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
//    public void onEntityShootBow(EntityShootBowEvent event) {
//        Entity projectile = event.getProjectile();
//
//        if (!(projectile instanceof Arrow)) {
//            return;
//        }
//
//        ItemStack bow = event.getBow();
//
//    }
//
//    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
//    public void onProjectileLaunch(ProjectileLaunchEvent event) {
//        Projectile projectile = event.getEntity();
//    }
//
//    /**
//     * Monitor EntityChangeBlock events.
//     *
//     * @param event
//     *            The event to watch
//     */
//
//
//    /**
//     * Handle EntityDamageByEntity events that involve modifying the event.
//     *
//     * @param event
//     *            The event to watch
//     */
//    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
//    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
//
//
//        if (event.getEntity() instanceof ArmorStand) {
//            return;
//        }
//
//        double damage = event.getFinalDamage();
//        Entity defender = event.getEntity();
//        Entity attacker = event.getDamager();
//
//        if (damage <= 0) {
//            if (defender instanceof Player && attacker instanceof Player) {
//                Player defendingPlayer = (Player) defender;
//                Player attackingPlayer = (Player) attacker;
//                if (event.getDamage(DamageModifier.ABSORPTION) > 0) {
//
//                        event.setCancelled(true);
//                        return;
//
//                }
//            }
//            return;
//        }
//
//        if (Misc.isNPCEntity(defender) || !defender.isValid() || !(defender instanceof LivingEntity)) {
//            return;
//        }
//
//        LivingEntity target = (LivingEntity) defender;
//
//        if (CombatUtils.isInvincible(target, damage)) {
//            return;
//        }
//
//
//        if (Misc.isNPCEntity(attacker)) {
//            return;
//        }
//
//        if (attacker instanceof Projectile) {
//            ProjectileSource projectileSource = ((Projectile) attacker).getShooter();
//
//            if (projectileSource instanceof LivingEntity) {
//                attacker = (LivingEntity) projectileSource;
//            }
//        }
//        else if (attacker instanceof Tameable) {
//            AnimalTamer animalTamer = ((Tameable) attacker).getOwner();
//
//            if (animalTamer != null && ((OfflinePlayer) animalTamer).isOnline()) {
//                attacker = (Entity) animalTamer;
//            }
//        }
//
//        if (defender instanceof Player && attacker instanceof Player) {
//            Player defendingPlayer = (Player) defender;
//            Player attackingPlayer = (Player) attacker;
//
//            if (!UserManager.hasPlayerDataKey(defendingPlayer) || !UserManager.hasPlayerDataKey(attackingPlayer)) {
//                return;
//            }
//
//            // We want to make sure we're not gaining XP or applying abilities
//            // when we hit ourselves
//            if (defendingPlayer.equals(attackingPlayer)) {
//                return;
//            }
//        }
//
//        CombatUtils.processCombatAttack(event, attacker, target);
//        CombatUtils.handleHealthbars(attacker, target, event.getFinalDamage());
//    }
//
//    /**
//     * Handle EntityDamage events that involve modifying the event.
//     *
//     * @param event
//     *            The event to modify
//     */
//    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
//    public void onEntityDamage(EntityDamageEvent event) {
//        double damage = event.getFinalDamage();
//
//        if (damage <= 0) {
//            return;
//        }
//
//        Entity entity = event.getEntity();
//
//        if (Misc.isNPCEntity(entity) || !entity.isValid() || !(entity instanceof LivingEntity)) {
//            return;
//        }
//
//        LivingEntity livingEntity = (LivingEntity) entity;
//
//        if (CombatUtils.isInvincible(livingEntity, damage)) {
//            return;
//        }
//
//        DamageCause cause = event.getCause();
//
//        if (livingEntity instanceof Player) {
//            Player player = (Player) entity;
//
//            if (!UserManager.hasPlayerDataKey(player)) {
//                return;
//            }
//
//            McMMOPlayer mcMMOPlayer = UserManager.getPlayer(player);
//
//            if (mcMMOPlayer.getGodMode()) {
//                event.setCancelled(true);
//                return;
//            }
//
//            switch (cause) {
//                case FALL:
//                    if (!SkillUtils.cooldownExpired((long) mcMMOPlayer.getTeleportATS(), Config.getInstance().getXPAfterTeleportCooldown())) {
//                        return;
//                    }
//
//                    AcrobaticsManager acrobaticsManager = mcMMOPlayer.getAcrobaticsManager();
//
//                    if (acrobaticsManager.canRoll()) {
//                        event.setDamage(acrobaticsManager.rollCheck(event.getDamage()));
//
//                        if (event.getFinalDamage() == 0) {
//                            event.setCancelled(true);
//                            return;
//                        }
//                    }
//                    break;
//
//                default:
//                    break;
//            }
//
//            if (event.getFinalDamage() >= 1) {
//                mcMMOPlayer.actualizeRecentlyHurt();
//            }
//        }
//        else if (livingEntity instanceof Tameable) {
//            Tameable pet = (Tameable) livingEntity;
//            AnimalTamer owner = pet.getOwner();
//
//            if (Taming.canPreventDamage(pet, owner)) {
//                Player player = (Player) owner;
//                Wolf wolf = (Wolf) pet;
//
//                TamingManager tamingManager = UserManager.getPlayer(player).getTamingManager();
//
//                switch (cause) {
//                    case CONTACT:
//                    case FIRE:
//                    case LAVA:
//                        if (tamingManager.canUseEnvironmentallyAware()) {
//                            tamingManager.processEnvironmentallyAware(wolf, event.getDamage());
//                        }
//                        return;
//
//                    case FALL:
//                        if (tamingManager.canUseEnvironmentallyAware()) {
//                            event.setCancelled(true);
//                        }
//                        return;
//
//                    case ENTITY_ATTACK:
//                    case PROJECTILE:
//                        if (tamingManager.canUseThickFur()) {
//                            event.setDamage(Taming.processThickFur(wolf, event.getDamage()));
//
//                            if (event.getFinalDamage() == 0) {
//                                event.setCancelled(true);
//                            }
//                        }
//                        return;
//
//                    case FIRE_TICK:
//                        if (tamingManager.canUseThickFur()) {
//                            Taming.processThickFurFire(wolf);
//                        }
//                        return;
//
//                    case MAGIC:
//                    case POISON:
//                    case WITHER:
//                        if (tamingManager.canUseHolyHound()) {
//                            Taming.processHolyHound(wolf, event.getDamage());
//                        }
//                        return;
//
//                    case BLOCK_EXPLOSION:
//                    case ENTITY_EXPLOSION:
//                    case LIGHTNING:
//                        if (tamingManager.canUseShockProof()) {
//                            event.setDamage(Taming.processShockProof(wolf, event.getDamage()));
//
//                            if (event.getFinalDamage() == 0) {
//                                event.setCancelled(true);
//                            }
//                        }
//                        return;
//
//                    default:
//                        return;
//                }
//            }
//        }
//    }
//
//    /**
//     * Monitor EntityDeath events.
//     *
//     * @param event
//     *            The event to watch
//     */
//    @EventHandler(priority = EventPriority.LOWEST)
//    public void onEntityDeathLowest(EntityDeathEvent event) {
//        LivingEntity entity = event.getEntity();
//
//        if (Misc.isNPCEntity(entity)) {
//            return;
//        }
//    }
//
//    /**
//     * Monitor EntityDeath events.
//     *
//     * @param event
//     *            The event to watch
//     */
//    @EventHandler(priority = EventPriority.MONITOR)
//    public void onEntityDeath(EntityDeathEvent event) {
//        LivingEntity entity = event.getEntity();
//
//        if (Misc.isNPCEntity(entity)) {
//            return;
//        }
//
//        BleedTimerTask.remove(entity);
//        Archery.arrowRetrievalCheck(entity);
//    }
//
//    /**
//     * Monitor CreatureSpawn events.
//     *
//     * @param event
//     *            The event to watch
//     */
//    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
//    public void onCreatureSpawn(CreatureSpawnEvent event) {
//        LivingEntity entity = event.getEntity();
//
//        switch (event.getSpawnReason()) {
//            case NETHER_PORTAL:
//            case SPAWNER:
//            case SPAWNER_EGG:
//
//
//                Entity passenger = entity.getPassenger();
//
//            case BREEDING:
//                return;
//
//            default:
//                return;
//        }
//    }
//
//    /**
//     * Handle ExplosionPrime events that involve modifying the event.
//     *
//     * @param event
//     *            The event to modify
//     */
//    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
//    public void onExplosionPrime(ExplosionPrimeEvent event) {
//        Entity entity = event.getEntity();
//
//        // We can make this assumption because we (should) be the only ones
//        // using this exact metadata
//        if (!UserManager.hasPlayerDataKey(player)) {
//            return;
//        }
//
//        MiningManager miningManager = UserManager.getPlayer(player).getMiningManager();
//
//        if (miningManager.canUseBiggerBombs()) {
//            event.setRadius(miningManager.biggerBombs(event.getRadius()));
//        }
//    }
//
//    /**
//     * Handle EntityExplode events that involve modifying the event.
//     *
//     * @param event
//     *            The event to modify
//     */
//    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
//    public void onEnitityExplode(EntityExplodeEvent event) {
//        Entity entity = event.getEntity();
//
//        if (!(entity instanceof TNTPrimed) || !entity.hasMetadata(CloverMMO.tntMetadataKey)) {
//            return;
//        }
//
//        // We can make this assumption because we (should) be the only ones
//        // using this exact metadata
//        Player player = plugin.getServer().getPlayerExact(entity.getMetadata(CloverMMO.tntMetadataKey).get(0).asString());
//
//        if (!UserManager.hasPlayerDataKey(player)) {
//            return;
//        }
//
//        MiningManager miningManager = UserManager.getPlayer(player).getMiningManager();
//
//        if (miningManager.canUseBlastMining()) {
//            miningManager.blastMiningDropProcessing(event.getYield(), event.blockList());
//            event.setYield(0);
//        }
//    }
//
//    /**
//     * Handle EntityExplode events that involve modifying the event.
//     *
//     * @param event
//     *            The event to modify
//     */
//    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
//    public void onEntityExplodeMonitor(EntityExplodeEvent event) {
//        Entity entity = event.getEntity();
//
//        if (!(entity instanceof TNTPrimed) || !entity.hasMetadata(CloverMMO.tntsafeMetadataKey)) {
//            return;
//        }
//
//        event.blockList().clear();
//    }
//
//    /**
//     * Handle FoodLevelChange events that involve modifying the event.
//     *
//     * @param event
//     *            The event to modify
//     */
//    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
//    public void onFoodLevelChange(FoodLevelChangeEvent event) {
//        Entity entity = event.getEntity();
//
//        if (!(entity instanceof Player)) {
//            return;
//        }
//
//        Player player = (Player) entity;
//
//        if (!UserManager.hasPlayerDataKey(player)) {
//            return;
//        }
//
//        int currentFoodLevel = player.getFoodLevel();
//        int newFoodLevel = event.getFoodLevel();
//        int foodChange = newFoodLevel - currentFoodLevel;
//
//        if (foodChange <= 0) {
//            return;
//        }
//
//        /*
//         * Some foods have 3 ranks Some foods have 5 ranks The number of ranks
//         * is based on how 'common' the item is We can adjust this quite easily
//         * if we find something is giving too much of a bonus
//         */
//        switch (player.getInventory().getItemInMainHand().getType()) {
//            case BAKED_POTATO: /*
//             * RESTORES 3 HUNGER - RESTORES 5 1/2 HUNGER @
//             * 1000
//             */
//            case BEETROOT:
//            case BREAD: /* RESTORES 2 1/2 HUNGER - RESTORES 5 HUNGER @ 1000 */
//            case CARROT_ITEM: /*
//             * RESTORES 2 HUNGER - RESTORES 4 1/2 HUNGER @
//             * 1000
//             */
//            case GOLDEN_CARROT: /*
//             * RESTORES 3 HUNGER - RESTORES 5 1/2 HUNGER @
//             * 1000
//             */
//            case MUSHROOM_SOUP: /*
//             * RESTORES 4 HUNGER - RESTORES 6 1/2 HUNGER @
//             * 1000
//             */
//            case PUMPKIN_PIE: /*
//             * RESTORES 4 HUNGER - RESTORES 6 1/2 HUNGER @
//             * 1000
//             */
//                if (Permissions.secondaryAbilityEnabled(player, SecondaryAbility.FARMERS_DIET)) {
//                    event.setFoodLevel(UserManager.getPlayer(player).getHerbalismManager().farmersDiet(Herbalism.farmersDietRankLevel1, newFoodLevel));
//                }
//                return;
//
//            case COOKIE: /* RESTORES 1/2 HUNGER - RESTORES 2 HUNGER @ 1000 */
//            case MELON: /* RESTORES 1 HUNGER - RESTORES 2 1/2 HUNGER @ 1000 */
//            case POISONOUS_POTATO: /*
//             * RESTORES 1 HUNGER - RESTORES 2 1/2 HUNGER
//             * @ 1000
//             */
//            case POTATO_ITEM: /* RESTORES 1/2 HUNGER - RESTORES 2 HUNGER @ 1000 */
//                if (Permissions.secondaryAbilityEnabled(player, SecondaryAbility.FARMERS_DIET)) {
//                    event.setFoodLevel(UserManager.getPlayer(player).getHerbalismManager().farmersDiet(Herbalism.farmersDietRankLevel2, newFoodLevel));
//                }
//                return;
//
//            case COOKED_FISH: /*
//             * RESTORES 2 1/2 HUNGER - RESTORES 5 HUNGER @
//             * 1000
//             */
//                if (Permissions.secondaryAbilityEnabled(player, SecondaryAbility.FISHERMANS_DIET)) {
//                    event.setFoodLevel(UserManager.getPlayer(player).getFishingManager().handleFishermanDiet(Fishing.fishermansDietRankLevel1, newFoodLevel));
//                }
//                return;
//
//            case RAW_FISH: /* RESTORES 1 HUNGER - RESTORES 2 1/2 HUNGER @ 1000 */
//                if (Permissions.secondaryAbilityEnabled(player, SecondaryAbility.FISHERMANS_DIET)) {
//                    event.setFoodLevel(UserManager.getPlayer(player).getFishingManager().handleFishermanDiet(Fishing.fishermansDietRankLevel2, newFoodLevel));
//                }
//                return;
//
//            default:
//                return;
//        }
//    }
//
//    /**
//     * Monitor EntityTame events.
//     *
//     * @param event
//     *            The event to watch
//     */
//
//    /**
//     * Handle EntityTarget events.
//     *
//     * @param event
//     *            The event to process
//     */
//    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
//    public void onEntityTarget(EntityTargetEvent event) {
//        Entity entity = event.getEntity();
//        Entity target = event.getTarget();
//
//        if (!(entity instanceof Tameable) || !(target instanceof Player)) {
//            return;
//        }
//
//        Player player = (Player) target;
//        Tameable tameable = (Tameable) entity;
//
//        if (!UserManager.hasPlayerDataKey(player) || !CombatUtils.isFriendlyPet(player, tameable)) {
//            return;
//        }
//
//        // isFriendlyPet ensures that the Tameable is: Tamed, owned by a player,
//        // and the owner is in the same party
//        // So we can make some assumptions here, about our casting and our check
//        if (!(Permissions.friendlyFire(player) && Permissions.friendlyFire((Player) tameable.getOwner()))) {
//            event.setCancelled(true);
//        }
//    }
//
//    /**
//     * Handle PotionSplash events in order to fix broken Splash Potion of
//     * Saturation.
//     *
//     * @param event
//     *            The event to process
//     */
//    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
//    public void onPotionSplash(PotionSplashEvent event) {
//        for (PotionEffect effect : ((PotionMeta) event.getPotion().getItem().getItemMeta()).getCustomEffects()) {
//            if (!effect.getType().equals(PotionEffectType.SATURATION)) {
//                return;
//            }
//
//            for (LivingEntity entity : event.getAffectedEntities()) {
//                int duration = (int) (effect.getDuration() * event.getIntensity(entity));
//                entity.addPotionEffect(new PotionEffect(effect.getType(), duration, effect.getAmplifier(), effect.isAmbient()));
//            }
//        }
//    }
//
//}