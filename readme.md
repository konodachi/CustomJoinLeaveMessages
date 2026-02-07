# CustomJoinLeaveMessages

A resilient, high-performance Spigot plugin that manages join and leave messages based on player permissions. Designed for server owners who need granular control over chat aesthetics without the bloat of massive essentials plugins.

## ⚡ Features

* **Rank-Based Messages:** Assign unique join/leave messages to different permission groups.
* **Priority System:** Uses a numeric priority system (Lower = Higher Priority) to ensure admins don't get the "Default" message.
* **Safe Mode Architecture:** If your `config.yml` breaks or goes missing, the plugin automatically switches to a **Memory-Only Fallback** state to prevent server crashes, while keeping your broken file intact for repairs.
* **Hex/Color Support:** Fully supports standard color codes (`&a`, `&l`) and formatting.
* **Performance:** Loads data into RAM on startup. Zero disk reading during player joins.

## 🛠️ Installation

1. Download the latest `.jar` release.
2. Drop it into your server's `plugins/` folder.
3. Restart the server.
4. Edit `plugins/CustomJoinLeaveMessages/config.yml` to customize your messages.
5. Run `/reloadConfig` to apply changes instantly.

## ⚙️ Configuration

The plugin uses a **Priority System**.

* **Lower Number = Higher Priority.**
* Example: A player with `priority: 0` (Owner) and `priority: 2` (Default) will show the Owner message.

```yaml
# This is the default configuration file. Make sure to have a backup before editing.

server-name: PlaceHolderSMP
first-time-message: "Welcome to %server-name%, %player-name%!"

roles:
  # Highest Priority (0 checks before 1)
  owner-tier:
    permission: customjoinleave.admin
    join-message: "Welcome back, boss!"
    leave-message: "Bye!"
    priority: 0

  vip-tier:
    permission: customjoinleave.vip
    join-message: "&6Welcome back, &4%player-name%&6! Thanks for your contributions!"
    leave-message: "Bye!"
    priority: 1

  # Lowest Priority (Fallback)
  default:
    permission: customjoinleave.default
    join-message: "&aWelcome back, &4%player-name%&a!"
    leave-message: "Bye!"
    priority: 10

```

## 📜 Commands & Permissions

| Command | Permission | Description |
| --- | --- | --- |
| `/reloadConfig` | `customjoinleave.reloadConfig` | Reloads the configuration file from disk. |
| (Passive) | `customjoinleave.default` | Given to all players by default. |