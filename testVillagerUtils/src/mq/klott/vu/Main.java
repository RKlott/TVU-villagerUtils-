package mq.klott.vu;



import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
//import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import mq.klott.vu.utils.Merchant;
import mq.klott.vu.utils.MerchantOffer;


@SuppressWarnings("deprecation")
public class Main extends  JavaPlugin implements Listener {
	
	public void onEnable() {
		
		System.out.println("[VillagerTrade] PLUGIN ENABLED");
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(this, this);
		
		
		
		
		
		
	}
	
	
	
	public void onDisable() {
		
	}
	
	  
	  Location locGunVillager = new Location(Bukkit.getWorld("world"), 474.247, 71, 253.529, (float) 93, (float) 4.5);
	  
	  Location locMunVillager = new Location(Bukkit.getWorld("world"), 474.236, 71, 255.667, (float) 93, (float) 4.5);
	  
	String gunVillager = "§4Traficant d'Arme";
	String munVillager = "§5Traficant de Munition";
	World world = Bukkit.getWorld("world");
	
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		Bukkit.broadcastMessage( p.getName()+" §eà rejoint le serveur de développement");
		
		p.getInventory().addItem(Crystal10());
		 
	}
	
	 @EventHandler
		public void onChat(PlayerChatEvent e) {
			  Player p = e.getPlayer();
			  e.setFormat(ChatColor.GOLD + p.getName() + ChatColor.GRAY+ " §l» " + ChatColor.YELLOW +e.getMessage());
			  p.setPlayerListName("§6 "+p.getName());
			  
			  Entity npc= world.spawn(locGunVillager, Villager.class);
			  ((Villager)npc).addPotionEffect( new PotionEffect(PotionEffectType.SLOW, 10000, 128));
			  ((Villager)npc).addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100000, 6));
			  ((Villager)npc).setCustomName(gunVillager);
			  ((Villager)npc).setCustomNameVisible(true);
			  
			  Entity npc2= world.spawn(locMunVillager, Villager.class);
			  ((Villager)npc2).addPotionEffect( new PotionEffect(PotionEffectType.SLOW, 10000, 128));
			  ((Villager)npc2).addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100000, 6));
			  ((Villager)npc2).setCustomName(munVillager);
			  ((Villager)npc2).setCustomNameVisible(true);
			  
		}
	 
	 @EventHandler
     public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent e) {
             if(e.getRightClicked() instanceof Villager){
            	 if(e.getRightClicked().getCustomName().equals(gunVillager)) {
                     e.setCancelled(true);
                             
                             Merchant m = new Merchant();
                             m.setTitle("§4Deal d'Arme");
                             m.setCustomer(e.getPlayer());
                             
                             m.addOffer(getOffer(Crystal10(), Material.BLAZE_ROD));
                           //  m.addOffer(getOffer(Material.DIAMOND_CHESTPLATE, Material.CACTUS));
                             
                           
     
                             m.openTrading(e.getPlayer());

            	 }else if(e.getRightClicked().getCustomName().equals(munVillager)) {
            		
                         e.setCancelled(true);
                                 
                                 Merchant m = new Merchant();
                                 m.setTitle("§5Deal de Munition");
                                 m.setCustomer(e.getPlayer());
                                 
                                 m.addOffer(getOffer(Crystal10(), Material.BLAZE_ROD));
                            //     m.addOffer(getOffer(Material.DIAMOND_CHESTPLATE, Material.CACTUS));
                                 
                                
         
                                 m.openTrading(e.getPlayer());
            	 } else {
            		 e.setCancelled(true);
            	 }
            	 }
             }
	 
	 public ItemStack Crystal10() {
			ItemStack is = new ItemStack(Material.STAINED_GLASS, 10, (byte)1);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName("§6Cristal");
			is.setItemMeta(im);
			return is;
		}
	 
            	 
             
	 
	 @EventHandler
	 public void onDmg(EntityDamageByEntityEvent e) {
		 Entity victim = e.getEntity();
		 if(victim.getCustomName().equals(gunVillager)) {
			 e.setCancelled(true);
		 }
	 }

     
     
     
     public static MerchantOffer getOffer(ItemStack arg1, Material arg2) {
             MerchantOffer o = new MerchantOffer(new ItemStack(arg1), new ItemStack(arg2));
             return o;
     }


	  
	
	

	
	

}
