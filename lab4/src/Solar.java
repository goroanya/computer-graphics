import java.awt.*;
import java.awt.event.*;
import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.behaviors.mouse.*;
import com.sun.j3d.utils.geometry.Sphere;

import java.applet.Applet;

public class Solar extends Applet {
    private Container frame;
    private BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 0), 100.0);

    public static void main(String[] args) {
        Solar s = new Solar();
        Frame f = new Frame();
        f.setSize(1366, 900);
        // set window properties
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        s.frame = f;
        s.display();
    }

    public void init() {
        frame = this;
        display();
    }

    public void display() {
        Transform3D mainTrans = new Transform3D();
        mainTrans.setTranslation(new Vector3d(0, 0, -10));
        TransformGroup mainGroup = new TransformGroup(mainTrans);

        // Sun globe
        TransformGroup sunGroup = new TransformGroup();
        createSun(sunGroup);
        mainGroup.addChild(sunGroup);

        // Mercury globe
        TransformGroup mercuryRotGroup = createGlobe(12000, sunGroup);

        Transform3D mercuryTrans = new Transform3D();
        mercuryTrans.setTranslation(new Vector3d(0, 0, 0.9));
        TransformGroup mercuryGroup = new TransformGroup(mercuryTrans);

        createMercury(mercuryGroup);
        mercuryRotGroup.addChild(mercuryGroup);

        // Venus globe
        TransformGroup venusRotGroup = createGlobe(28800, sunGroup);

        Transform3D venusTrans = new Transform3D();
        venusTrans.setTranslation(new Vector3d(0, 0, -1.3));
        TransformGroup venusGroup = new TransformGroup(venusTrans);

        createVenus(venusGroup);
        venusRotGroup.addChild(venusGroup);

        // Earth globe
        TransformGroup earthRotGroup = createGlobe(48000, sunGroup);

        Transform3D earthTrans = new Transform3D();
        earthTrans.setTranslation(new Vector3d(0, 0, -4));
        TransformGroup earthGroup = new TransformGroup(earthTrans);

        createEarth(earthGroup);
        earthRotGroup.addChild(earthGroup);

        // Moon globe
        Transform3D firstMoonTrans = new Transform3D();
        firstMoonTrans.rotX(1);
        TransformGroup firstMoonGroup = new TransformGroup(firstMoonTrans);
        earthGroup.addChild(firstMoonGroup);

        TransformGroup moonRotGroup = createGlobe(4000, firstMoonGroup);

        Transform3D moonTrans = new Transform3D();
        moonTrans.setTranslation(new Vector3d(0, 0, -0.1));
        TransformGroup moonGroup = new TransformGroup(moonTrans);

        createMoon(moonGroup);
        moonRotGroup.addChild(moonGroup);


        // create some background illumination
        AmbientLight starLight = new AmbientLight(true, new Color3f(0.4f, 0.4f, 0.4f));
        starLight.setInfluencingBounds(bounds);
        mainGroup.addChild(starLight);

        // Mars globe
        TransformGroup marsRotGroup = createGlobe(90000, sunGroup);

        Transform3D marsTrans = new Transform3D();
        marsTrans.setTranslation(new Vector3d(0, 0, -2.1));
        TransformGroup marsGroup = new TransformGroup(marsTrans);

        createMars(marsGroup);
        marsRotGroup.addChild(marsGroup);

        // Jupiter globe
        TransformGroup jupiterRotGroup = createGlobe(480000, sunGroup);
        Transform3D jupiterTrans = new Transform3D();
        jupiterTrans.setTranslation(new Vector3d(0, 0, -5));
        TransformGroup jupiterGroup = new TransformGroup(jupiterTrans);

        createJupiter(jupiterGroup);
        jupiterRotGroup.addChild(jupiterGroup);

        // Saturn globe
        TransformGroup saturnRotGroup = createGlobe(1440000, sunGroup);
        Transform3D saturnTrans = new Transform3D();
        saturnTrans.setTranslation(new Vector3d(0, 0, -9));
        TransformGroup saturnGroup = new TransformGroup(saturnTrans);

        createSaturn(saturnGroup);
        saturnRotGroup.addChild(saturnGroup);

        // --------------- add neptune globe -----------------
        TransformGroup neptuneRotGroup = createGlobe(8064000, sunGroup);

        Transform3D neptuneTrans = new Transform3D();
        neptuneTrans.setTranslation(new Vector3d(0, 0, -30));
        TransformGroup neptuneGroup = new TransformGroup(neptuneTrans);

        createNeptune(neptuneGroup);
        neptuneRotGroup.addChild(neptuneGroup);

        // Uranus globe
        TransformGroup uranusRotGroup = createGlobe(4032000, sunGroup);

        Transform3D uranusTrans = new Transform3D();
        uranusTrans.setTranslation(new Vector3d(0, 0, -19));
        TransformGroup uranusGroup = new TransformGroup(uranusTrans);

        createUranus(uranusGroup);
        uranusRotGroup.addChild(uranusGroup);

        // Pluto globe
        TransformGroup plutoRotGroup = createGlobe(11904000, sunGroup);

        Transform3D plutoTrans = new Transform3D();
        plutoTrans.setTranslation(new Vector3d(-0.2, -0.2, -36));
        TransformGroup plutoGroup = new TransformGroup(plutoTrans);

        createPluto(plutoGroup);
        plutoRotGroup.addChild(plutoGroup);

        // -------------- mouse movements ----------------------

        // allow runtime modification
        mainGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        mainGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        // rotate with mouse
        MouseRotate rotate = new MouseRotate(mainGroup);
        rotate.setFactor(0.02);
        rotate.setSchedulingBounds(bounds);
        mainGroup.addChild(rotate);

        // zoom with mouse
        MouseZoom zoom = new MouseZoom(mainGroup);
        zoom.setFactor(0.02);
        zoom.setSchedulingBounds(bounds);
        mainGroup.addChild(zoom);

        // move with mouse
        MouseTranslate translate = new MouseTranslate(mainGroup);
        translate.setFactor(0.02);
        translate.setSchedulingBounds(bounds);
        mainGroup.addChild(translate);

        // create root of branch graph
        BranchGroup root = new BranchGroup();
        root.addChild(mainGroup);
        root.compile();

        // init universe
        Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        SimpleUniverse universe = new SimpleUniverse(canvas);
        universe.addBranchGraph(root);
        universe.getViewingPlatform().setNominalViewingTransform();

        canvas.resize(20, 20);

        frame.add(BorderLayout.CENTER, canvas);

        frame.setVisible(true);
    }

    public TransformGroup createGlobe(int rotSpeed, TransformGroup mainGroup) {
        Transform3D planetRotTrans = new Transform3D();
        TransformGroup planetRotGroup = new TransformGroup(planetRotTrans);

        Alpha planetAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE, 0, 0, rotSpeed, 0, 0, 0, 0, 0);

        RotationInterpolator planetRotator = new RotationInterpolator(planetAlpha, planetRotGroup);
        planetRotator.setSchedulingBounds(bounds);

        planetRotGroup.addChild(planetRotator);
        planetRotGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        mainGroup.addChild(planetRotGroup);

        return planetRotGroup;
    }

    public void createPlanet(float planetSize, int selfRotSpeed, String textureFile, TransformGroup group, boolean emit) {
        TransformGroup lastGroup;
        if (selfRotSpeed != 0) {
            BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
            Transform3D rotTrans = new Transform3D();
            TransformGroup rotGroup = new TransformGroup(rotTrans);
            Alpha rotAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE, 0, 0, selfRotSpeed, 0, 0, 0, 0, 0);
            RotationInterpolator rotator = new RotationInterpolator(rotAlpha, rotGroup);
            rotator.setSchedulingBounds(bounds);
            rotGroup.addChild(rotator);
            rotGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            group.addChild(rotGroup);
            lastGroup = rotGroup;
        } else {
            lastGroup = group;
        }

        Sphere planet = new Sphere(planetSize, Sphere.GENERATE_NORMALS + Sphere.GENERATE_TEXTURE_COORDS, 25, Utils.loadTexture(textureFile, emit));
        lastGroup.addChild(planet);

        if (emit) {
            PointLight sunLight = new PointLight(true, new Color3f(1.0f, 1.0f, 1.0f),
                    new Point3f(0.0f, 0.0f, 0.0f),
                    new Point3f(1.0f, 0.0f, 0.0f));
            sunLight.setInfluencingBounds(bounds);
            lastGroup.addChild(sunLight);
        }
    }

    private void createSun(TransformGroup group) {
        createPlanet(0.5f, 100000, "source\\sunmap.jpg", group, true);
    }

    private void createEarth(TransformGroup group) {
        createPlanet(0.08f, 1000, "source\\earthmap1k.jpg", group, false);
    }

    private void createMoon(TransformGroup group) {
        createPlanet(0.02f, 0, "source\\moonmap1k.jpg", group, false);
    }

    private void createMercury(TransformGroup group) {
        createPlanet(0.04f, 30, "source\\mercurymap.jpg", group, false);
    }

    private void createVenus(TransformGroup group) {
        createPlanet(0.08f, 10, "source\\venusmap.jpg", group, false);
    }

    private void createMars(TransformGroup group) {
        createPlanet(0.05f, 1000, "source\\marsmap1k.jpg", group, false);
    }

    private void createJupiter(TransformGroup group) {
        createPlanet(0.4f, 2400, "source\\jupitermap.jpg", group, false);
    }

    private void createSaturn(TransformGroup group) {
        createPlanet(0.35f, 2200, "source\\saturnmap.jpg", group, false);
    }

    private void createUranus(TransformGroup group) {
        createPlanet(0.2f, 1250, "source\\uranusmap.jpg", group, false);
    }

    private void createNeptune(TransformGroup group) {
        createPlanet(0.2f, 1350, "source\\neptunemap.jpg", group, false);
    }

    private void createPluto(TransformGroup group) {
        createPlanet(0.03f, 100, "source\\plutomap1k.jpg", group, false);
    }
}