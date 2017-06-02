# SerializeMe

A Simple Minecraft Plugin to Serialize Itemstacks to HOCON!

SerializeMe was orignially created as a debugging tool, but has been release in the hopes that it may be useful to other plugin developers / server administrators.  SerializeMe allows a player to convert an itemstack into HOCON format for the purpose of examining any custom data attached to the object, as well as for storage for later retrival.

## Commands

* `/sm` - Displays version information about the SerializeMe Plugin. (Aliases: sm, serializeme)  
* `/sm s [-c]` - Prints the item currently held in the player's hand in HOCON format to the chat, has the optional `-c` flag to print in "compact" mode (Aliases: s, serialize)  . 
* `/sm d <hocon>` - Deserializes the string `hocon` into an itemstack and places it into the player's selected hotbar slot, the slot must be empty. **Note:** It is recomended that "compact" format HOCON be used for deserialization. (Aliases: d, deserialize) 

## Permissions
`serializeme.command.info`  
`serializeme.command.serialize`  
`serializeme.command.deserialize`  

## Examples

* Running `/sm s` while holding a _Potion of Weakness_
```
ContentVersion=1
Count=1
ItemType="minecraft:potion"
UnsafeDamage=0
UnsafeData {
    Potion="minecraft:weakness"
}
```

* Running `/sm s -c` while holding a _Potion of Weakness_
```
{"ContentVersion":1,"Count":1,"ItemType":"minecraft:potion","UnsafeDamage":0,"UnsafeData":{"Potion":"minecraft:weakness"}}
```

* Converting the serialized _Potion of Weakness_ back into an itemstack
```
/sm d {"ContentVersion":1,"Count":1,"ItemType":"minecraft:potion","UnsafeDamage":0,"UnsafeData":{"Potion":"minecraft:weakness"}}
```

## Support Me
I will **never** charge money for the use of my plugins, however they do require a significant amount of work to maintain and update. If you'd like to show your support and buy me a cup of tea sometime (I don't drink that horrid coffee stuff :P) you can do so [here](https://www.paypal.me/zerthick)
