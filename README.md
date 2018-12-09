# CLJ Commons Formatter

In the last ten years or so, source code formatters with limited/no configuration have become popular. Go is the most well known example, shipping with gofmt, but there are similar tools in Rust, JavaScript, Python.

**CLJ Commons believes it would be useful for the Clojure community to be able to develop (or adopt) a single source-code formatter which is able to format Clojure source code to a canonical format.** The purpose of this repository is to help develop the problem space, hear from different stakeholders, and determine whether such a tool is desired, possible, and likely to be useful. It seems unlikely that 100% of the community would want such a tool, but we feel like there is enough desire for a common formatting tool that this could still be valuable.

You can see the announcement of this project along with discussion of the goals, motivations, and potential benefits for building this tool at [ClojureVerse](https://clojureverse.org/t/clj-commons-building-a-formatter-like-gofmt-for-clojure/3240).

## Deliverables

* Come to a common agreement amongst the community on a style for formatting Clojure code
* Create a spec for formatting Clojure code
* Create a test-suite for verifying adherence to the spec
* Create a reference implementation formatting tool

## Getting Involved

At this stage, we are looking to explore the problem space, hear from different stakeholders, and start wrestling with some of the tricky formatting decisions that will need to be made. No particular tool or formatting style has been chosen yet, though we welcome suggestions here.

General discussion about the idea can go on [ClojureVerse](https://clojureverse.org/t/clj-commons-building-a-formatter-like-gofmt-for-clojure/3240). If you'd like to discuss a particular formatting question, open an issue on this repository, or comment on one that is already there.

For a tool like this to have the best chance of success, it needs to get input from a wide variety of people. If you've got thoughts, please share them.

Thanks!
