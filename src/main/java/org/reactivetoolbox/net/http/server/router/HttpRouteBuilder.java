package org.reactivetoolbox.net.http.server.router;

import org.reactivetoolbox.core.async.Promise;
import org.reactivetoolbox.core.lang.Functions.FN0;
import org.reactivetoolbox.core.lang.Functions.FN1;
import org.reactivetoolbox.core.lang.Functions.FN2;
import org.reactivetoolbox.core.lang.Functions.FN3;
import org.reactivetoolbox.core.lang.Functions.FN4;
import org.reactivetoolbox.core.lang.Functions.FN5;
import org.reactivetoolbox.core.lang.Functions.FN6;
import org.reactivetoolbox.core.lang.Functions.FN7;
import org.reactivetoolbox.core.lang.Functions.FN8;
import org.reactivetoolbox.core.lang.Functions.FN9;
import org.reactivetoolbox.core.lang.Result;
import org.reactivetoolbox.core.lang.Tuple;
import org.reactivetoolbox.core.lang.Tuple.Tuple1;
import org.reactivetoolbox.core.lang.Tuple.Tuple2;
import org.reactivetoolbox.core.lang.Tuple.Tuple3;
import org.reactivetoolbox.core.lang.Tuple.Tuple4;
import org.reactivetoolbox.core.lang.Tuple.Tuple5;
import org.reactivetoolbox.core.lang.Tuple.Tuple6;
import org.reactivetoolbox.core.lang.Tuple.Tuple7;
import org.reactivetoolbox.core.lang.Tuple.Tuple8;
import org.reactivetoolbox.core.lang.Tuple.Tuple9;
import org.reactivetoolbox.net.http.ContentType;
import org.reactivetoolbox.net.http.Method;
import org.reactivetoolbox.net.http.server.RequestContext;

import static org.reactivetoolbox.core.lang.Result.success;
import static org.reactivetoolbox.core.lang.Tuple.Tuple0;
import static org.reactivetoolbox.core.lang.Tuple.tuple;
import static org.reactivetoolbox.net.http.Method.CONNECT;
import static org.reactivetoolbox.net.http.Method.DELETE;
import static org.reactivetoolbox.net.http.Method.GET;
import static org.reactivetoolbox.net.http.Method.HEAD;
import static org.reactivetoolbox.net.http.Method.OPTIONS;
import static org.reactivetoolbox.net.http.Method.PATCH;
import static org.reactivetoolbox.net.http.Method.POST;
import static org.reactivetoolbox.net.http.Method.PUT;
import static org.reactivetoolbox.net.http.Method.TRACE;

public final class HttpRouteBuilder {
    //TODO: implement grouping under common sub-path
    //static List<Route> at(String path), Route)

    static Stage1 options() {
        return new Builder(OPTIONS);
    }

    static Stage1 options(final String path) {
        return new Builder(OPTIONS, path);
    }

    static Stage1 get() {
        return new Builder(GET);
    }

    static Stage1 get(final String path) {
        return new Builder(GET, path);
    }

    static Stage1 head() {
        return new Builder(HEAD);
    }

    static Stage1 head(final String path) {
        return new Builder(HEAD, path);
    }

    static Stage1 post() {
        return new Builder(POST);
    }

    static Stage1 post(final String path) {
        return new Builder(POST, path);
    }

    static Stage1 put() {
        return new Builder(PUT);
    }

    static Stage1 put(final String path) {
        return new Builder(PUT, path);
    }

    static Stage1 patch() {
        return new Builder(PATCH);
    }

    static Stage1 patch(final String path) {
        return new Builder(PATCH, path);
    }

    static Stage1 delete() {
        return new Builder(DELETE);
    }

    static Stage1 delete(final String path) {
        return new Builder(DELETE, path);
    }

    static Stage1 trace() {
        return new Builder(TRACE);
    }

    static Stage1 trace(final String path) {
        return new Builder(TRACE, path);
    }

    static Stage1 connect() {
        return new Builder(CONNECT);
    }

    static Stage1 connect(final String path) {
        return new Builder(CONNECT, path);
    }

    interface MutableRoute extends Route {
    }

    private static class Builder implements Stage1 {
        private final Method method;
        private final String path;
        private ContentType input = ContentType.JSON;
        private ContentType output = ContentType.JSON;

        private Builder(final Method method) {
            this(method, "");
        }

        private Builder(final Method method, final String path) {
            this.method = method;
            this.path = path;
        }

        @Override
        public Stage1 input(final ContentType input) {
            this.input = input;
            return this;
        }

        @Override
        public Stage1 output(final ContentType output) {
            this.output = output;
            return this;
        }

        @Override
        public Stage2_0 withoutParameters() {
            return new Stage2_0() {
                @Override
                public Result<Tuple0> extract(final RequestContext context) {
                    return success(tuple());
                }

                @Override
                public <R> MutableRoute buildRoute(final FN1<Promise<R>, RequestContext> handler) {
                    return Builder.this.buildRoute(handler);
                }
            };
        }

        @Override
        public <T1> Stage2_1<T1> with(final Parameter<T1> p1) {
            return new Stage2_1<T1>() {
                @Override
                public Result<Tuple1<T1>> extract(final RequestContext context) {
                    return tuple(p1.apply(context)).map(Result::zip);
                }

                @Override
                public <R> MutableRoute buildRoute(final FN1<Promise<R>, RequestContext> handler) {
                    return Builder.this.buildRoute(handler);
                }
            };
        }

        @Override
        public <T1, T2> Stage2_2<T1, T2> with(final Parameter<T1> p1, final Parameter<T2> p2) {
            return new Stage2_2<T1, T2>() {
                @Override
                public Result<Tuple2<T1, T2>> extract(final RequestContext context) {
                    return tuple(p1.apply(context), p2.apply(context)).map(Result::zip);
                }

                @Override
                public <R> MutableRoute buildRoute(final FN1<Promise<R>, RequestContext> handler) {
                    return Builder.this.buildRoute(handler);
                }
            };
        }

        @Override
        public <T1, T2, T3> Stage2_3<T1, T2, T3> with(final Parameter<T1> p1, final Parameter<T2> p2, final Parameter<T3> p3) {
            return new Stage2_3<T1, T2, T3>() {
                @Override
                public Result<Tuple3<T1, T2, T3>> extract(final RequestContext context) {
                    return tuple(p1.apply(context), p2.apply(context), p3.apply(context)).map(Result::zip);
                }

                @Override
                public <R> MutableRoute buildRoute(final FN1<Promise<R>, RequestContext> handler) {
                    return Builder.this.buildRoute(handler);
                }
            };
        }

        @Override
        public <T1, T2, T3, T4> Stage2_4<T1, T2, T3, T4> with(final Parameter<T1> p1,
                                                              final Parameter<T2> p2,
                                                              final Parameter<T3> p3,
                                                              final Parameter<T4> p4) {
            return new Stage2_4<T1, T2, T3, T4>() {
                @Override
                public Result<Tuple4<T1, T2, T3, T4>> extract(final RequestContext context) {
                    return tuple(p1.apply(context), p2.apply(context), p3.apply(context), p4.apply(context)).map(Result::zip);
                }

                @Override
                public <R> MutableRoute buildRoute(final FN1<Promise<R>, RequestContext> handler) {
                    return Builder.this.buildRoute(handler);
                }
            };
        }

        @Override
        public <T1, T2, T3, T4, T5> Stage2_5<T1, T2, T3, T4, T5> with(final Parameter<T1> p1,
                                                                      final Parameter<T2> p2,
                                                                      final Parameter<T3> p3,
                                                                      final Parameter<T4> p4,
                                                                      final Parameter<T5> p5) {
            return new Stage2_5<T1, T2, T3, T4, T5>() {
                @Override
                public Result<Tuple5<T1, T2, T3, T4, T5>> extract(final RequestContext context) {
                    return tuple(p1.apply(context), p2.apply(context), p3.apply(context),
                                 p4.apply(context), p5.apply(context)).map(Result::zip);
                }

                @Override
                public <R> MutableRoute buildRoute(final FN1<Promise<R>, RequestContext> handler) {
                    return Builder.this.buildRoute(handler);
                }
            };
        }

        @Override
        public <T1, T2, T3, T4, T5, T6> Stage2_6<T1, T2, T3, T4, T5, T6> with(final Parameter<T1> p1,
                                                                              final Parameter<T2> p2,
                                                                              final Parameter<T3> p3,
                                                                              final Parameter<T4> p4,
                                                                              final Parameter<T5> p5,
                                                                              final Parameter<T6> p6) {
            return new Stage2_6<T1, T2, T3, T4, T5, T6>() {
                @Override
                public Result<Tuple6<T1, T2, T3, T4, T5, T6>> extract(final RequestContext context) {
                    return tuple(p1.apply(context), p2.apply(context), p3.apply(context),
                                 p4.apply(context), p5.apply(context), p6.apply(context)).map(Result::zip);
                }

                @Override
                public <R> MutableRoute buildRoute(final FN1<Promise<R>, RequestContext> handler) {
                    return Builder.this.buildRoute(handler);
                }
            };
        }

        @Override
        public <T1, T2, T3, T4, T5, T6, T7> Stage2_7<T1, T2, T3, T4, T5, T6, T7> with(final Parameter<T1> p1,
                                                                                      final Parameter<T2> p2,
                                                                                      final Parameter<T3> p3,
                                                                                      final Parameter<T4> p4,
                                                                                      final Parameter<T5> p5,
                                                                                      final Parameter<T6> p6,
                                                                                      final Parameter<T7> p7) {
            return new Stage2_7<T1, T2, T3, T4, T5, T6, T7>() {
                @Override
                public Result<Tuple7<T1, T2, T3, T4, T5, T6, T7>> extract(final RequestContext context) {
                    return tuple(p1.apply(context), p2.apply(context), p3.apply(context), p4.apply(context),
                                 p5.apply(context), p6.apply(context), p7.apply(context)).map(Result::zip);
                }

                @Override
                public <R> MutableRoute buildRoute(final FN1<Promise<R>, RequestContext> handler) {
                    return Builder.this.buildRoute(handler);
                }
            };
        }

        @Override
        public <T1, T2, T3, T4, T5, T6, T7, T8> Stage2_8<T1, T2, T3, T4, T5, T6, T7, T8> with(final Parameter<T1> p1,
                                                                                              final Parameter<T2> p2,
                                                                                              final Parameter<T3> p3,
                                                                                              final Parameter<T4> p4,
                                                                                              final Parameter<T5> p5,
                                                                                              final Parameter<T6> p6,
                                                                                              final Parameter<T7> p7,
                                                                                              final Parameter<T8> p8) {
            return new Stage2_8<T1, T2, T3, T4, T5, T6, T7, T8>() {
                @Override
                public Result<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>> extract(final RequestContext context) {
                    return tuple(p1.apply(context), p2.apply(context), p3.apply(context), p4.apply(context),
                                 p5.apply(context), p6.apply(context), p7.apply(context), p8.apply(context)).map(Result::zip);
                }

                @Override
                public <R> MutableRoute buildRoute(final FN1<Promise<R>, RequestContext> handler) {
                    return Builder.this.buildRoute(handler);
                }
            };
        }

        @Override
        public <T1, T2, T3, T4, T5, T6, T7, T8, T9> Stage2_9<T1, T2, T3, T4, T5, T6, T7, T8, T9> with(final Parameter<T1> p1,
                                                                                                      final Parameter<T2> p2,
                                                                                                      final Parameter<T3> p3,
                                                                                                      final Parameter<T4> p4,
                                                                                                      final Parameter<T5> p5,
                                                                                                      final Parameter<T6> p6,
                                                                                                      final Parameter<T7> p7,
                                                                                                      final Parameter<T8> p8,
                                                                                                      final Parameter<T9> p9) {
            return new Stage2_9<T1, T2, T3, T4, T5, T6, T7, T8, T9>() {
                @Override
                public Result<Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>> extract(final RequestContext context) {
                    return tuple(p1.apply(context), p2.apply(context), p3.apply(context), p4.apply(context), p5.apply(context),
                                 p6.apply(context), p7.apply(context), p8.apply(context), p9.apply(context)).map(Result::zip);
                }

                @Override
                public <R> MutableRoute buildRoute(final FN1<Promise<R>, RequestContext> handler) {
                    return Builder.this.buildRoute(handler);
                }
            };
        }

        public <R> MutableRoute buildRoute(final FN1<Promise<R>, RequestContext> handler) {
            return null;
        }
    }

    private interface Stage1 {
        Stage1 input(final ContentType input);

        Stage1 output(final ContentType output);

        Stage2_0 withoutParameters();

        <T1> Stage2_1<T1> with(final Parameter<T1> p1);

        <T1, T2> Stage2_2<T1, T2> with(final Parameter<T1> p1, final Parameter<T2> p2);

        <T1, T2, T3> Stage2_3<T1, T2, T3> with(final Parameter<T1> p1, final Parameter<T2> p2, final Parameter<T3> p3);

        <T1, T2, T3, T4> Stage2_4<T1, T2, T3, T4> with(final Parameter<T1> p1, final Parameter<T2> p2,
                                                       final Parameter<T3> p3, final Parameter<T4> p4);

        <T1, T2, T3, T4, T5> Stage2_5<T1, T2, T3, T4, T5> with(final Parameter<T1> p1, final Parameter<T2> p2, final Parameter<T3> p3,
                                                               final Parameter<T4> p4, final Parameter<T5> p5);

        <T1, T2, T3, T4, T5, T6> Stage2_6<T1, T2, T3, T4, T5, T6> with(final Parameter<T1> p1, final Parameter<T2> p2, final Parameter<T3> p3,
                                                                       final Parameter<T4> p4, final Parameter<T5> p5, final Parameter<T6> p6);

        <T1, T2, T3, T4, T5, T6, T7> Stage2_7<T1, T2, T3, T4, T5, T6, T7> with(final Parameter<T1> p1, final Parameter<T2> p2, final Parameter<T3> p3,
                                                                               final Parameter<T4> p4, final Parameter<T5> p5, final Parameter<T6> p6,
                                                                               final Parameter<T7> p7);

        <T1, T2, T3, T4, T5, T6, T7, T8> Stage2_8<T1, T2, T3, T4, T5, T6, T7, T8> with(final Parameter<T1> p1, final Parameter<T2> p2,
                                                                                       final Parameter<T3> p3, final Parameter<T4> p4,
                                                                                       final Parameter<T5> p5, final Parameter<T6> p6,
                                                                                       final Parameter<T7> p7, final Parameter<T8> p8);

        <T1, T2, T3, T4, T5, T6, T7, T8, T9> Stage2_9<T1, T2, T3, T4, T5, T6, T7, T8, T9> with(final Parameter<T1> p1, final Parameter<T2> p2,
                                                                                               final Parameter<T3> p3, final Parameter<T4> p4,
                                                                                               final Parameter<T5> p5, final Parameter<T6> p6,
                                                                                               final Parameter<T7> p7, final Parameter<T8> p8,
                                                                                               final Parameter<T9> p9);
    }

    private interface Stage2<T extends Tuple> {
        Result<T> extract(final RequestContext context);

        <R> MutableRoute buildRoute(final FN1<Promise<R>, RequestContext> handler);
    }

    private interface Stage2_0 extends Stage2<Tuple0> {
        default <R> MutableRoute apply(FN0<Result<R>> fn) {
            final FN1<Result<Tuple0>, RequestContext> vv = this::extract;

            return buildRoute(vv.then(tuple -> tuple.flatMap(fn.bindTuple())).then(Promise::ready));
        }

        default <R> MutableRoute then(FN0<Promise<R>> fn) {
            final FN1<Result<Tuple0>, RequestContext> vv = this::extract;

            return buildRoute(vv.then(result -> result.map(Promise::readyFail, fn.bindTuple())));
        }
    }

    private interface Stage2_1<T1> extends Stage2<Tuple1<T1>> {
        default <R> MutableRoute apply(final FN1<Result<R>, T1> fn) {
            final FN1<Result<Tuple1<T1>>, RequestContext> vv = this::extract;

            return buildRoute(vv.then(tuple -> tuple.flatMap(fn.bindTuple())).then(Promise::ready));
        }

        default <R> MutableRoute then(final FN1<Promise<R>, T1> fn) {
            final FN1<Result<Tuple1<T1>>, RequestContext> vv = this::extract;

            return buildRoute(vv.then(result -> result.map(Promise::readyFail, fn.bindTuple())));
        }
    }

    private interface Stage2_2<T1, T2> extends Stage2<Tuple2<T1, T2>> {
        default <R> MutableRoute apply(FN2<Result<R>, T1, T2> fn) {
            final FN1<Result<Tuple2<T1, T2>>, RequestContext> vv = this::extract;

            return buildRoute(vv.then(tuple -> tuple.flatMap(fn.bindTuple())).then(Promise::ready));
        }

        default <R> MutableRoute then(FN2<Promise<R>, T1, T2> fn) {
            final FN1<Result<Tuple2<T1, T2>>, RequestContext> vv = this::extract;

            return buildRoute(vv.then(result -> result.map(Promise::readyFail, fn.bindTuple())));
        }
    }

    private interface Stage2_3<T1, T2, T3> extends Stage2<Tuple3<T1, T2, T3>> {
        default <R> MutableRoute apply(FN3<Result<R>, T1, T2, T3> fn) {
            final FN1<Result<Tuple3<T1, T2, T3>>, RequestContext> vv = this::extract;

            return buildRoute(vv.then(tuple -> tuple.flatMap(fn.bindTuple())).then(Promise::ready));
        }

        default <R> MutableRoute then(FN3<Promise<R>, T1, T2, T3> fn) {
            final FN1<Result<Tuple3<T1, T2, T3>>, RequestContext> vv = this::extract;

            return buildRoute(vv.then(result -> result.map(Promise::readyFail, fn.bindTuple())));
        }
    }

    private interface Stage2_4<T1, T2, T3, T4> extends Stage2<Tuple4<T1, T2, T3, T4>> {
        default <R> MutableRoute apply(FN4<Result<R>, T1, T2, T3, T4> fn) {
            final FN1<Result<Tuple4<T1, T2, T3, T4>>, RequestContext> vv = this::extract;

            return buildRoute(vv.then(tuple -> tuple.flatMap(fn.bindTuple())).then(Promise::ready));
        }

        default <R> MutableRoute then(FN4<Promise<R>, T1, T2, T3, T4> fn) {
            final FN1<Result<Tuple4<T1, T2, T3, T4>>, RequestContext> vv = this::extract;

            return buildRoute(vv.then(result -> result.map(Promise::readyFail, fn.bindTuple())));
        }
    }

    private interface Stage2_5<T1, T2, T3, T4, T5> extends Stage2<Tuple5<T1, T2, T3, T4, T5>> {
        default <R> MutableRoute apply(FN5<Result<R>, T1, T2, T3, T4, T5> fn) {
            final FN1<Result<Tuple5<T1, T2, T3, T4, T5>>, RequestContext> vv = this::extract;

            return buildRoute(vv.then(tuple -> tuple.flatMap(fn.bindTuple())).then(Promise::ready));
        }

        default <R> MutableRoute then(FN5<Promise<R>, T1, T2, T3, T4, T5> fn) {
            final FN1<Result<Tuple5<T1, T2, T3, T4, T5>>, RequestContext> vv = this::extract;

            return buildRoute(vv.then(result -> result.map(Promise::readyFail, fn.bindTuple())));
        }
    }

    private interface Stage2_6<T1, T2, T3, T4, T5, T6> extends Stage2<Tuple6<T1, T2, T3, T4, T5, T6>> {
        default <R> MutableRoute apply(FN6<Result<R>, T1, T2, T3, T4, T5, T6> fn) {
            final FN1<Result<Tuple6<T1, T2, T3, T4, T5, T6>>, RequestContext> vv = this::extract;

            return buildRoute(vv.then(tuple -> tuple.flatMap(fn.bindTuple())).then(Promise::ready));
        }

        default <R> MutableRoute then(FN6<Promise<R>, T1, T2, T3, T4, T5, T6> fn) {
            final FN1<Result<Tuple6<T1, T2, T3, T4, T5, T6>>, RequestContext> vv = this::extract;

            return buildRoute(vv.then(result -> result.map(Promise::readyFail, fn.bindTuple())));
        }
    }

    private interface Stage2_7<T1, T2, T3, T4, T5, T6, T7> extends Stage2<Tuple7<T1, T2, T3, T4, T5, T6, T7>> {
        default <R> MutableRoute apply(FN7<Result<R>, T1, T2, T3, T4, T5, T6, T7> fn) {
            final FN1<Result<Tuple7<T1, T2, T3, T4, T5, T6, T7>>, RequestContext> vv = this::extract;

            return buildRoute(vv.then(tuple -> tuple.flatMap(fn.bindTuple())).then(Promise::ready));
        }

        default <R> MutableRoute then(FN7<Promise<R>, T1, T2, T3, T4, T5, T6, T7> fn) {
            final FN1<Result<Tuple7<T1, T2, T3, T4, T5, T6, T7>>, RequestContext> vv = this::extract;

            return buildRoute(vv.then(result -> result.map(Promise::readyFail, fn.bindTuple())));
        }
    }

    private interface Stage2_8<T1, T2, T3, T4, T5, T6, T7, T8> extends Stage2<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>> {
        default <R> MutableRoute apply(FN8<Result<R>, T1, T2, T3, T4, T5, T6, T7, T8> fn) {
            final FN1<Result<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>>, RequestContext> vv = this::extract;

            return buildRoute(vv.then(tuple -> tuple.flatMap(fn.bindTuple())).then(Promise::ready));
        }

        default <R> MutableRoute then(FN8<Promise<R>, T1, T2, T3, T4, T5, T6, T7, T8> fn) {
            final FN1<Result<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>>, RequestContext> vv = this::extract;

            return buildRoute(vv.then(result -> result.map(Promise::readyFail, fn.bindTuple())));
        }
    }

    private interface Stage2_9<T1, T2, T3, T4, T5, T6, T7, T8, T9> extends Stage2<Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>> {
        default <R> MutableRoute apply(FN9<Result<R>, T1, T2, T3, T4, T5, T6, T7, T8, T9> fn) {
            final FN1<Result<Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>>, RequestContext> vv = this::extract;

            return buildRoute(vv.then(tuple -> tuple.flatMap(fn.bindTuple())).then(Promise::ready));
        }

        default <R> MutableRoute then(FN9<Promise<R>, T1, T2, T3, T4, T5, T6, T7, T8, T9> fn) {
            final FN1<Result<Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>>, RequestContext> vv = this::extract;

            return buildRoute(vv.then(result -> result.map(Promise::readyFail, fn.bindTuple())));
        }
    }
}
