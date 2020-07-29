# logic-builder
An ugly & interactive logic gate visualizer written in Java using plain old Java Swing\
Contributions are more than welcome!

## Idea
The idea for this program came to mind shortly after I made my little alu representation. I was building a little ripple carry adder in the popular sandbox game Minecraft when I suddenly got the idea for this program.

## Use cases
This program can be used to understand how logic gates work, visualize complex structures or just play around with different gates.

## Controls
- Hold left click to drag nodes / draw connections
- Right click to remove a node
- Hold middle click to move the whole screen

## Available gates
- AND, OR, XOR, NOT (Inverter)
- Splitter (Takes a signal and outputs the same signal twice)

The following are not really gates, but I don't know where else I could list them:
- Input (Can be altered by the user; Double click to toggle the state)
- Output

## Known bugs
- StackOverflowError when creating infinite recursion (#1)

## Todo
- Finish javadoc comments (#2)
- Add Json exporter and importer (#3)
- Add a tool to create custom gates from Json exports (#4)
- Maybe add more gates?

## Gallery
<details>
<summary>Click to expand</summary>

<img src="https://nobody-will.make-america-great-aga.in/nQvECTDNdeNnKlKR" alt="Picture 1"><br>

<img src="https://nobody-will.make-america-great-aga.in/PbkkPDavKIyMyUyR" alt="Picture 2"><br>

<img src="https://nobody-will.make-america-great-aga.in/oHKOUfAIduqUzmZN" alt="Picture 3">
</details>

## License
[MIT](LICENSE)
