# logic-builder
<!-- ALL-CONTRIBUTORS-BADGE:START - Do not remove or modify this section -->
[![All Contributors](https://img.shields.io/badge/all_contributors-1-orange.svg?style=flat-square)](#contributors-)
<!-- ALL-CONTRIBUTORS-BADGE:END -->
An ugly & interactive logic gate visualizer written in Java using plain old Java Swing\
Contributions are more than welcome!

## Download
[![GitHub All Releases](https://img.shields.io/github/downloads/RealCerus/logic-builder/total?style=for-the-badge)](https://github.com/RealCerus/logic-builder/releases/latest)

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
- BCD to 7-segment display gate

The following are not really gates, but I don't know where else I could list them:
- Input (Can be altered by the user; Double click to toggle the state)
- BCD Input (Converts a decimal into a binary-coded decimal)
- Output
- 7-Segment display output

## Known bugs
- StackOverflowError when creating infinite recursion ([#1](https://github.com/RealCerus/logic-builder/issues/1))

## Todo
- Finish javadoc comments ([#2](https://github.com/RealCerus/logic-builder/issues/2))
- Add Json exporter and importer ([#3](https://github.com/RealCerus/logic-builder/issues/3))
- Add a tool to create custom gates from Json exports ([#4](https://github.com/RealCerus/logic-builder/issues/4))
- Maybe add more gates?

## Gallery
<details>
<summary>Click to expand</summary>

<img src="https://nobody-will.make-america-great-aga.in/nQvECTDNdeNnKlKR" alt="Picture 1"><br>

<img src="https://nobody-will.make-america-great-aga.in/PbkkPDavKIyMyUyR" alt="Picture 2"><br>

<img src="https://nobody-will.make-america-great-aga.in/oHKOUfAIduqUzmZN" alt="Picture 3"><br>

<img src="https://cerus-dev.de/img/logic-builder-7-segment.gif" alt="Picture 4">
</details>

## License
[MIT](LICENSE)

## Contributors ✨

Thanks goes to these wonderful people ([emoji key](https://allcontributors.org/docs/en/emoji-key)):

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tr>
    <td align="center"><a href="https://cerus-dev.de"><img src="https://avatars3.githubusercontent.com/u/46848982?v=4" width="100px;" alt=""/><br /><sub><b>Maximilian</b></sub></a><br /><a href="https://github.com/RealCerus/logic-builder/issues?q=author%3ARealCerus" title="Bug reports">🐛</a> <a href="https://github.com/RealCerus/logic-builder/commits?author=RealCerus" title="Code">💻</a> <a href="https://github.com/RealCerus/logic-builder/commits?author=RealCerus" title="Documentation">📖</a> <a href="#ideas-RealCerus" title="Ideas, Planning, & Feedback">🤔</a></td>
  </tr>
</table>

<!-- markdownlint-enable -->
<!-- prettier-ignore-end -->
<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors) specification. Contributions of any kind welcome!