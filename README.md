# yKitPvP
PS: Questo core non è stato idealizzato da me, ho semplicemente ripreso il progetto in mano io
    essendo che il creatore Ricky non è più attivo ne continua il progetto.
    Il plugin è stato creato con la base di zKitPvP di conseguenza continuato da Ricky che
    ormai non è più attivo, ma rilascerò presto aggiornamenti io.
    Qui troverete la versione ufficiale non rilasciata dal creatore, ma io continuerò ad aggiornarlo
    aggiungendo sempre più funzioni!

KitPvP Core per realizzare il miglior server KitPvP. Richiede PAPI & Vault.

# Permessi
Il plugin ha permessi unici per ogni comando, qui puoi trovare tutti i permessi:
* `kitpvp.command.admin` - /kitpvp
* `kitpvp.command.alert` - /alert
* `kitpvp.command.build` - /build
* `kitpvp.command.reload` - /reload
* `kitpvp.command.setbounty` - /setbounty
* `kitpvp.command.setspawn` - /setspawn
* `kitpvp.command.setstats` - /setstats
* `kitpvp.command.tpall` - /tpall
* `kitpvp.command.tp` - /tp
* `kitpvp.command.tphere` - /tphere
* `kitpvp.command.gmc` - /gmc
* `kitpvp.command.gmsp` - /gmsp
* `kitpvp.command.gms` - /gms
* `kitpvp.command.discord` - /discord, /ds
* `kitpvp.command.dropsettings` - /dropsettings
* `kitpvp.command.fix` - /fix, /ripara
* `kitpvp.command.fly` - /fly
* `kitpvp.command.givexp` - /givexp
* `kitpvp.command.invsee` - /invsee
* `kitpvp.command.spawn` - /spawn
* `kitpvp.command.stack` - /stack, /pot
* `kitpvp.command.store` - /store, /buy
* `kitpvp.command.trash` - /trash, /cestino
* `kitpvp.command.vanish` - /vanish, /v
* `kitpvp.command.vote` - /vote, /vota

# Comandi
In questa versione (1.0) ci sono 26 comandi:
* `/kitpvp` - Comando principale.
* `/alert` - Per mandare un messaggio globale all'interno del server.
* `/build` - Per attivare la modalità costruzione. (Senza essa non potrai spaccare blocchi)
* `/reload` - Per aggiornare tutti i config. (Non consigliato, piuttosto riavvia tutto il server)
* `/setbounty` - Per impostare una taglia ad un giocatore.
* `/setspawn` - Per impostare il punto di spawn.
* `/setstats` - Per modificare le statistiche di un giocatore.
* `/tpall` - Per teletrasportare tutti i giocatori da te.
* `/tp` - Per teletrasportare un giocatore da un altro giocatore.
* `/tphere` - Per teletrasportare un giocatore nella tua posizione.
* `/gmc` - Per mettere te o un altro giocatore in creativa.
* `/gmsp` - Per mettere te o un altro giocatore in spettatore.
* `/gms` - Per mettere te o un altro giocatore in sopravvivenza.
* `/discord` - Per mostrare il link del server discord del server.
* `/dropsettings` - Per gestire le impostazioni di raccolta dei drop.
* `/fix` - Per aggiustare il tuo inventario.
* `/fly` - Per mettere in fly te o un altro giocatore.
* `/givexp` - Per dare ad un altro giocatore i tuoi livelli di XP.
* `/invsee` - Per controllare l'inventario di un altro giocatore.
* `/spawn` - Per tornare allo spawn.
* `/stack` - Per unire le pozioni tutte in un unico inventario.
* `/store` - Per mostrare il link discord del server.
* `/trash` - Per aprire il cestino.
* `/vanish` - Per mettere in vanish te o un altro player.
* `/vote` - Per mostrare il link per votare il server.
* `/stats` - Per vedere le statistiche di un giocatore (non funziona al momento.)

# PlaceHolders
Il plugin supporta PlaceholderAPI e registra i seguenti placeholder:
* `%kitpvp_kills%` - The total player's kills
* `%kitpvp_deaths%` - The total player's deaths
* `%kitpvp_streak%` - The player current kill streak
* `%kitpvp_combat%` - The player combat cooldown
* `%kitpvp_enderpearl%` - The player enderpearl cooldown
* `%kitpvp_bounty%` - The player bounty as number
* `%kitpvp_bounty_formatted%` - The player bounty formatted with K, M, B, ecc...

# Funzioni
* Ascie che tolgono più danno all'armor
* Timer Enderpearl & Combattimento
* Statistiche
* Taglie
* Punto di spawn
* Effetti/item dopo ogni kill
* Obsidian Breaker
* No fall damage quando scendi da spawn
* Protezione spawn
* Possibilità di unire tutte le pozioni in un unico slot
* Possibilità di gestire la raccolta dei drop
