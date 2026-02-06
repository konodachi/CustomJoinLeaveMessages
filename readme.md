# CustomJoinLeaveMessages

![Java](https://img.shields.io/badge/Java-21-orange) ![Spigot](https://img.shields.io/badge/Platform-Spigot%201.21-lightgrey) ![License](https://img.shields.io/badge/License-MIT-blue)

A lightweight, high-performance Spigot plugin that manages join and leave messages based on player permissions. Designed for server owners who need granular control over chat aesthetics without the bloat of massive essentials plugins.

## ⚡ Features

* **Rank-Based Messages:** Assign unique join/leave messages to different permission groups (Owner, VIP, Default, etc.).
* **First-Join Support:** Special welcome broadcast for players joining for the first time.
* **Hex/Color Support:** Fully supports standard color codes (`&a`, `&l`, etc.).
* **Lightweight:** No database required. Loads config once at startup for maximum performance.
* **Fail-Safe:** Built-in default values ensure the plugin never crashes, even if configuration entries are missing.

## 🛠️ Installation

1.  Download the latest `.jar` release.
2.  Drop it into your server's `plugins/` folder.
3.  Restart the server.
4.  Edit `plugins/CustomJoinLeaveMessages/config.yml` to customize your messages.
5.  Restart or reload to apply changes.

## ⚙️ Configuration

The plugin reads roles from **top to bottom**. Place your highest priority ranks (e.g., Owner, Admin) at the top of the list.

```yaml
# This is the default configuration file. Make sure to have a backup before editing.

server-name: PlaceHolderSMP
first-time-message: "Welcome to %server-name%, %player-name%!"

# Put the most important ranks in the top. The file is read top to bottom.
roles:
  owner-tier:
    permission: customjoinleave.admin
    join-message: "Welcome back, boss!"
    leave-message: "Bye!"

  vip-tier:
    permission: customjoinleave.vip
    join-message: "&6Welcome back, &4%player-name%&6! Thanks for your contributions!"
    leave-message: "Bye!"

  default:
    permission: customjoinleave.default
    join-message: "&aWelcome back, &4%player-name%&a!"
    leave-message: "Bye!"