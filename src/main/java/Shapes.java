import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.Camera3D;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.TransformComponent;
import com.almasb.fxgl.scene3d.Prism;
import com.almasb.fxgl.scene3d.Torus;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.onKey;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class Shapes extends GameApplication {

    private Camera3D camera;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1200);
        settings.setHeight(720);
        settings.set3D(true);
        settings.setTitle("3D Shapes");
    }

    @Override
    protected void initInput() {
        onKey(KeyCode.W, () -> camera.moveForward());
        onKey(KeyCode.S, () -> camera.moveBack());
        onKey(KeyCode.D, () -> camera.moveRight());
        onKey(KeyCode.A, () -> camera.moveLeft());

        onKey(KeyCode.L, () -> getGameController().exit());

        onKey(KeyCode.F, () -> {});
    }

    @Override
    protected void initGame() {
        camera = getGameScene().getCamera3D();
        TransformComponent transform = camera.getTransform();
        transform.translateZ(-10);
        transform.translateY(0);

        getGameScene().setBackgroundColor(Color.LIGHTBLUE);
        getGameScene().setFPSCamera(true);
        getGameScene().setCursorInvisible();

        Node[] shapes = makeShapes();

        int x = 0;

        for (Node shape : shapes) {
            Entity e = makeEntity(x*2-8,0,6);
            e.getViewComponent().addChild(shape);

            animationBuilder()
                    .interpolator(Interpolators.SMOOTH.EASE_OUT())
                    .delay(Duration.seconds(x * 0.5))
                    .repeatInfinitely()
                    .autoReverse(true)
                    .translate(e)
                    .from(e.getPosition3D())
                    .to(e.getPosition3D().add(0, -2, 0))
                    .buildAndPlay();

            animationBuilder()
                    //.interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                    .duration(Duration.seconds(2.0))
                    .delay(Duration.seconds(x * 0.5))
                    .repeatInfinitely()
                    .autoReverse(true)
                    .rotate(e)
                    .from(new Point3D(0, 0, 0))
                    .to(new Point3D(360, 0, 0))
                    .buildAndPlay();

            x++;
        }
    }

    private Node[] makeShapes(){
        return new Node[]{
                new Box(),
                new Sphere(),
                new Torus(),
                new Prism(1,0.5,2,3),
                new Prism(),
                new Prism(1,1,2,4),
                new Prism(1,1,2,5),
                new Prism(1,1,2,6),
                new Cylinder()
        };
    }

    private Entity makeEntity(double x, double y, double z) {
        return FXGL.entityBuilder()
                .at(x,y,z)
                .buildAndAttach();
    }

    public static void main(String[] args) {
        launch(args);
    }
}