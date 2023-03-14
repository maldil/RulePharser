package org.python.core;


@Untraversable
class PySystemStateFunctions extends PyBuiltinFunctionSet {

    PySystemStateFunctions(String name, int index, int minargs, int maxargs) {
        super(name, index, minargs, maxargs);
    }

    @Override
    public PyObject __call__(PyObject arg) {
        switch (index) {
            case 10:
                PySystemState.displayhook(arg);
                return Py.None;
            default:
                throw info.unexpectedCall(1, false);
        }
    }

    @Override
    public PyObject __call__(PyObject arg1, PyObject arg2, PyObject arg3) {
        switch (index) {
            case 30:
                PySystemState.excepthook(arg1, arg2, arg3);
                return Py.None;
            default:
                throw info.unexpectedCall(3, false);
        }
    }
}
